package pren.zhl.tool.controller;


import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.service.IAccountService;
import pren.zhl.tool.service.IUserService;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author zhl
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/tool/user")
public class UserController {

    @Resource
    private IAccountService iAccountService;

    @Resource
    private IUserService iUserService;

    @Resource
    private Response response;

    @RequiresPermissions("/tool/user/add")
    @ApiModelProperty(value = "新增用户")
    @PostMapping("/add")
    public Response Add(AccountDTO accountDTO){
        Integer flag = iAccountService.register(accountDTO);
        if(flag == 1){
            return response.success("新增用户成功");
        }else if (flag == -1){
            response.failure("用户名为空！");
        }else if (flag == -2){
            response.failure("密码为空！");
        }else if (flag == -3){
            response.failure("昵称能为空!");
        }
        return response.failure("新增用户失败！");
    }

    @RequiresPermissions("/tool/user/update")
    @ApiModelProperty(value = "修改用户")
    @PostMapping("/update")
    public Response Update(User user){
        if (iUserService.updateById(user))
            return response.success("更新用户成功");
        return response.failure("修改失败");
    }

    @RequiresPermissions("/tool/user/delete")
    @ApiModelProperty(value = "删除用户")
    @DeleteMapping("/{userId}")
    public Response Delete(@PathVariable Long userId){
        if (iAccountService.delete(userId))
            return response.success("删除用户成功");
        return response.failure("删除失败");
    }
}
