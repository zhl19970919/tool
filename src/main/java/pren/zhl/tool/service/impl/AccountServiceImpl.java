package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sun.xml.internal.ws.api.pipe.Tube;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import pren.zhl.tool.bean.CacheUser;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Account;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.mapper.AccountMapper;
import pren.zhl.tool.mapper.UserMapper;
import pren.zhl.tool.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pren.zhl.tool.service.IUserService;
import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
@Primary
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private IUserService iUserService;

    @Resource
    private UserMapper userMapper;


    @Override
    public Account findByUsername(String username) {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<Account>().eq(Account::getOpenCode, username)
        );
    }

    @Override
    public AccountDTO findByOpencode(String username,String userDeleted, String userId) {
        return accountMapper.findByOpencode(username,userDeleted,userId);
    }

    @Override
    public CacheUser login(String userName, String password) {

        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);


        CacheUser cacheUser = null;
        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            AccountDTO accountDTO = (AccountDTO) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(accountDTO, cacheUser);
            log.warn("CacheUser is {}");
        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            return CacheUser.builder().text("账户不存在异常!").build();
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            return CacheUser.builder().text("凭据错误（密码错误）异常!").build();
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            return CacheUser.builder().text("身份验证异常!").build();
        }
        return cacheUser;
    }

    @Override
    public Integer register(AccountDTO accountDTO){
        String userName = accountDTO.getOpenCode();
        String password = accountDTO.getPassword();
        String name = accountDTO.getName();
        if (StringUtils.isBlank(userName))
            return  -1;
        if (StringUtils.isBlank(password))
            return  -2;
        if (StringUtils.isBlank(name))
            return  -3;
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        accountDTO.setPassword(new Md5Hash(accountDTO.getPassword(),salt,2).toString());
        accountDTO.setSalt(salt);
        Account account = new Account();
        User user = new User();
        BeanUtils.copyProperties(accountDTO,account);
        BeanUtils.copyProperties(accountDTO,user);
        user.setId(userMapper.getNextId());
        account.setUserId(user.getId());
        QueryWrapper<Account> queryWrapper = new QueryWrapper();
        queryWrapper.eq("open_code",accountDTO.getOpenCode());
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0){
            return 0;
        }else{
            try {
                iUserService.save(user);
                baseMapper.insert(account);
                return 1;
            }catch (Exception e){
                log.error("用户注册插库报错",e);
                //事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return 0;
    }


    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public List<Account> listAccounts() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public Boolean delete(Long userId){
        User user = new User();
        Account account = new Account();
        user.setId(userId);
        user.setDeleted(true);
        account.setDeleted(true);
        try {
            iUserService.updateById(user);
            UpdateWrapper<Account> updateWrapper = new UpdateWrapper<Account>();
            updateWrapper.eq("user_id",userId);
            accountMapper.update(account,updateWrapper);
            return true;
        }catch (Exception e){
            log.error("用户删除报错",e);
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }


}
