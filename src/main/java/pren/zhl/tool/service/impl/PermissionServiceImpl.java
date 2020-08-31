package pren.zhl.tool.service.impl;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.entity.Permission;
import pren.zhl.tool.mapper.PermissionMapper;
import pren.zhl.tool.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
@Primary
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getRolePermissions(Long roleId) {
        return permissionMapper.getRolePermissions(roleId);
    }
}
