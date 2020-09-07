package pren.zhl.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestParam;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Permission;
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
    public Page<Role> getRoles(Page<Role> page){
        Page<Role> rolePage = roleMapper.getRoles(page);
        rolePage.setRecords(new Utils().getRoleMenu(rolePage.getRecords(),new ArrayList<Role>()));
        return rolePage;
    }

    @Override
    public Boolean delete(Long id){
        Role role = new Role();
        role.setDeleted(true);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        try {
            roleMapper.update(role,queryWrapper);
            role.setId(id);
            roleMapper.updateById(role);
            return true;
        }catch (Exception e){
            log.error("权限删除报错",e);
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    public Boolean BatchDelete(List<Long> ids){
        for (int i = 0; i < ids.size(); i++) {
            Role role = new Role();
            role.setDeleted(true);
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",ids.get(i));
            try {
                roleMapper.update(role,queryWrapper);
                role.setId(ids.get(i));
                roleMapper.updateById(role);
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
