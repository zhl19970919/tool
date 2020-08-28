package pren.zhl.tool.service.impl;

import pren.zhl.tool.entity.Role;
import pren.zhl.tool.mapper.RoleMapper;
import pren.zhl.tool.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> getUserRoles(Long userId) {
        return roleMapper.getUserRoles(userId);
    }
}
