package pren.zhl.tool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.entity.Permission;
import pren.zhl.tool.service.IPermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@Api(value="权限controller",tags={"权限操作接口"})
@RestController
@RequestMapping("/tool/permission")
public class PermissionController {

    @Resource
    private IPermissionService iPermissionService;

    @Resource
    private Response response;

    @RequiresPermissions("/tool/permission")
    @ApiOperation(value = "权限列表")
    @GetMapping
    public Response index(Page<Permission> page){
        return response.success("获取权限列表成功",iPermissionService.getPermissionList(page));
    }

    @RequiresPermissions("/tool/permission/tree")
    @ApiOperation(value = "获取权限列表树")
    @GetMapping("/tree")
    public Response tree(){
        return response.success("获取权限树列表成功",iPermissionService.getPermissionTree());
    }

    @RequiresPermissions("/tool/permission/add")
    @ApiOperation(value = "增加权限")
    @PostMapping("/add")
    public Response add(Permission permission){
        if (StringUtils.isBlank(permission.getName()))
            return response.failure("权限名称不能为空");
        else if (StringUtils.isBlank(permission.getIntro()))
            return response.failure("权限路径不能为空");
        else if (permission.getCategory() == null)
            return response.failure("权限类别不能为空");
        else if (permission.getUri() == null)
            return response.failure("Url规则不能为空");
        else {
            iPermissionService.save(permission);
            return response.success("增加权限成功");
        }
    }

    @RequiresPermissions("/tool/permission/update")
    @ApiOperation(value = "修改权限")
    @PutMapping("/update")
    public Response update(Permission permission){
        if (iPermissionService.updateById(permission))
            return response.success("修改权限成功");
        return response.failure("修改权限失败");
    }

    @RequiresPermissions("/tool/permission/delete")
    @ApiOperation(value = "删除权限")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam(name = "id", value = "权限id", required = true) @PathVariable Long id){
        if (iPermissionService.delete(id))
            return response.success("删除权限成功");
        return response.failure("删除权限失败");
    }

    @RequiresPermissions("/tool/permission/batchDelete")
    @ApiOperation(value = "批量删除权限")
    @DeleteMapping("/batchDelete")
    public Response batcDelete(@ApiParam(name = "ids", value = "权限ids", required = true) @RequestBody List<Long> ids){
        if (iPermissionService.batchDelete(ids))
            return response.success("批量删除权限成功");
        return response.failure("批量删除权限失败");
    }
}
