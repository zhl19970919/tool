package pren.zhl.tool.service;

import org.springframework.context.annotation.Primary;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */

public interface IRoleService extends IService<Role> {
    List<RoleDTO> getUserRoles(Long userId);
}
