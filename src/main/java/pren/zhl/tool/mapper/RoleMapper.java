package pren.zhl.tool.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleDTO> getUserRoles(@Param("userId") Long userId);

    Page<Role> getRoles(Page<Role> page);
}
