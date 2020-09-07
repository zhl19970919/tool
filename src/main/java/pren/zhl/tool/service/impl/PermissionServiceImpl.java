package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import pren.zhl.tool.entity.Permission;
import pren.zhl.tool.mapper.PermissionMapper;
import pren.zhl.tool.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pren.zhl.tool.utils.Utils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public Page<Permission> getPermissionList(Page<Permission> page){
        return  permissionMapper.getPermissionList(page);
    }

    @Override
    public List<Permission> getPermissionTree(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted",0);
        List<Permission> permissions = new ArrayList<>();
        return new Utils().getAppMenu(permissionMapper.getPermissionList(),permissions);
    }

    @Override
    public Boolean delete(Long id){
        Permission permission = new Permission();
        permission.setDeleted(true);
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        try {
            permissionMapper.update(permission,queryWrapper);
            permission.setId(id);
            permissionMapper.updateById(permission);
            return true;
        }catch (Exception e){
            log.error("权限删除报错",e);
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    public Boolean batchDelete(List<Long> ids){
        for (int i = 0; i < ids.size(); i++) {
            Permission permission = new Permission();
            permission.setDeleted(true);
            QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",ids.get(i));
            try {
                permissionMapper.update(permission,queryWrapper);
                permission.setId(ids.get(i));
                permissionMapper.updateById(permission);
            }catch (Exception e){
                log.error("批量权限删除报错",e);
                //事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }
}
