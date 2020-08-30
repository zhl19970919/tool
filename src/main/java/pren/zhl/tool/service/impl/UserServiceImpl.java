package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
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
    public List<User> listUsers() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }
}
