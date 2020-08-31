package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import pren.zhl.tool.bean.CacheUser;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Account;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.mapper.AccountMapper;
import pren.zhl.tool.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pren.zhl.tool.service.IUserService;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private IUserService iUserService;

    @Resource
    private IAccountService iAccountService;

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
            try {
                throw new LoginException("账号不存在!");
            } catch (LoginException loginException) {
                loginException.printStackTrace();
            }
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            try {
                throw new LoginException("密码不正确!");
            } catch (LoginException loginException) {
                loginException.printStackTrace();
            }
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
            try {
                throw new LoginException("用户验证失败!");
            } catch (LoginException loginException) {
                loginException.printStackTrace();
            }
        }
        return cacheUser;
    }

    @Override
    public Boolean register(AccountDTO accountDTO){
        Account account = new Account();
        User user = new User();
        BeanUtils.copyProperties(accountDTO,account);
        BeanUtils.copyProperties(accountDTO,user);
        user.setId(accountDTO.getUserId());
        Integer count = iUserService.countUserById(user.getId());
        if(count > 0){
            return false;
        }else{
            try {
                iUserService.save(user);
                iAccountService.save(account);
                return true;
            }catch (Exception e){
                log.error("用户注册插库报错");
                e.printStackTrace();
                iUserService.removeById(user.getId());
                iAccountService.removeById(account.getId());
            }
        }
        return false;
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


}
