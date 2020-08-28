package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import pren.zhl.tool.bean.CacheUser;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.mapper.UserMapper;
import pren.zhl.tool.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.security.auth.login.LoginException;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User findByUsername(String username) {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getName, username)
        );
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
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            log.warn("CacheUser is {}", cacheUser.toString());
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
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public List<User> listUsers() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
}