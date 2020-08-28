package pren.zhl.tool.mapper;

import org.apache.ibatis.annotations.Param;
import pren.zhl.tool.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> getRolePermissions(@Param("roleId") Long roleId);
}
