package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.context.annotation.Primary;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Role;
import pren.zhl.tool.mapper.RoleMapper;
import pren.zhl.tool.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pren.zhl.tool.utils.Utils;

import java.util.ArrayList;
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
@Primary
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getUserRoles(Long userId) {
        return roleMapper.getUserRoles(userId);
    }

    @Override
    public  Page<Role> getRoles(Page<Role> page){
        Page<Role> rolePage = roleMapper.getRoles(page);
        rolePage.setRecords(new Utils().getRoleMenu(rolePage.getRecords(),new ArrayList<Role>()));
        return rolePage;
    }
}
