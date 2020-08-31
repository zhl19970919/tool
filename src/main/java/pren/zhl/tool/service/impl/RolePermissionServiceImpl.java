package pren.zhl.tool.service.impl;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.entity.RolePermission;
import pren.zhl.tool.mapper.RolePermissionMapper;
import pren.zhl.tool.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
@Primary
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
