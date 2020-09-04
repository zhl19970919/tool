package pren.zhl.tool.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.User;
import pren.zhl.tool.service.IAccountService;
import pren.zhl.tool.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

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

    @RequiresPermissions("/tool/user")
    @ApiModelProperty("用户管理主页")
    @GetMapping
    public Response index(Page<AccountDTO> page){
        Page<AccountDTO> accountDTOList = iAccountService.getAccountList(page);
        if (accountDTOList.getRecords() != null && accountDTOList.getRecords().size() > 0)
            return response.success("获取用户列表成功",accountDTOList);
        return response.failure("获取用户列表失败");
    }

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
        }else if (flag == -4){
            response.failure("用户ID不能为空!");
        }
        return response.failure("新增用户失败！");
    }

    @RequiresPermissions("/tool/user/update")
    @ApiModelProperty(value = "修改用户")
    @PutMapping("/update")
    public Response Update(AccountDTO accountDTO){
        if (iAccountService.update(accountDTO))
            return response.success("更新用户成功");
        return response.failure("修改失败");
    }


    @RequiresPermissions("/tool/user/delete")
    @ApiModelProperty(value = "禁用用户")
    @DeleteMapping("/{userId}")
    public Response Delete(@PathVariable Long userId){
        if (iAccountService.delete(userId))
            return response.success("删除用户成功");
        return response.failure("删除失败");
    }

    @RequiresPermissions("/tool/user/recover")
    @ApiModelProperty(value = "恢复用户")
    @PutMapping("/{userId}")
    public Response Recover(@PathVariable Long userId){
        if (iAccountService.recover(userId))
            return response.success("恢复用户成功");
        return response.failure("恢复用户失败");
    }

    @RequiresPermissions("/tool/user/ResetPwd")
    @ApiModelProperty(value = "重置密码")
    @PutMapping("/reset/{userId}")
    public Response ResetPwd(@PathVariable String userId){
        return response.failure("重置密码失败");
    }
}
