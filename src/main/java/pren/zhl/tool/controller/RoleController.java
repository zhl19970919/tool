package pren.zhl.tool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Role;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.service.IRoleService;

import javax.annotation.Resource;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/tool/role")
@Slf4j
public class RoleController {

    @Resource
    private IRoleService iRoleService;

    @Resource
    private Response response;

    @RequiresPermissions("/tool/role")
    @ApiModelProperty(value = "角色管理主页")
    @GetMapping
    public Response index(Page<Role> page){
        Page<Role> rolePage = iRoleService.getRoles(page);
        if (rolePage.getRecords() != null && rolePage.getRecords().size() > 0)
            return response.success("获取角色列表成功",rolePage);
        return response.failure("获取角色列表失败");
    }

    @RequiresPermissions("/tool/role/add")
    @ApiModelProperty(value = "新增角色")
    @PostMapping("/add")
    public Response add(Role role){
        if (role.getParentId() == null)
            return response.failure("父角色ID不能为空");
        else if (role.getName() == null && role.getName().trim().length() == 0)
            return response.failure("角色名称不能为空");
        else{
            boolean flag = iRoleService.save(role);
            if (flag)
                return response.success("新增角色成功");
            else
                return response.failure("新增角色失败");
        }
    }

    @RequiresPermissions("/tool/role/update")
    @ApiModelProperty(value = "修改角色")
    @PutMapping("/update")
    public Response update(Role role){
        if (role.getParentId() == null)
            return response.failure("父角色ID不能为空");
        else if (role.getName() == null && role.getName().trim().length() == 0)
            return response.failure("角色名称不能为空");
        else{
            boolean flag = iRoleService.updateById(role);
            if (flag)
                return response.success("修改角色成功");
            else
                return response.failure("修改角色失败");
        }
    }

    @RequiresPermissions("/tool/role/update")
    @ApiModelProperty(value = "删除角色")
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id, Role role){
        role.setDeleted(true);
        role.setId(id);
        boolean flag = iRoleService.updateById(role);
        if (flag)
            return response.success("删除角色成功");
        else
            return response.failure("删除角色失败");
    }
}
