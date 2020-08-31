package pren.zhl.tool.service.impl;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.entity.UserRole;
import pren.zhl.tool.mapper.UserRoleMapper;
import pren.zhl.tool.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
@Primary
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
