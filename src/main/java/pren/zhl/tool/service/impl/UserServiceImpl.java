package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.mapper.UserMapper;
import pren.zhl.tool.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Wrapper;
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
@Primary
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> listUsers() {
        return baseMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public  User findUserById(Long id){return  baseMapper.selectById(id);}

    @Override
    public  Integer countUserById(Long id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return  baseMapper.selectCount(queryWrapper);}
}
