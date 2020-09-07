package pren.zhl.tool.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pren.zhl.tool.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */

public interface IPermissionService extends IService<Permission> {
    List<Permission> getRolePermissions(@Param("roleId") Long roleId);

    Page<Permission> getPermissionList(Page<Permission> page);

    List<Permission> getPermissionTree();

    Boolean delete (Long id);

    Boolean batchDelete(List<Long> ids);
}
