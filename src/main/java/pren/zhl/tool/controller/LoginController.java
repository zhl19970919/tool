package pren.zhl.tool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pren.zhl.tool.bean.CacheUser;
import pren.zhl.tool.bean.Response;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.service.IAccountService;
import pren.zhl.tool.service.IUserService;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:55
 * @description：登录Controller
 */
@Slf4j
@RestController
@RequestMapping("/")
@Api(value="登录controller",tags={"登录接口"})
public class LoginController {

    @Resource
    private IUserService iUserService;

    @Resource
    private IAccountService iAccountService;

    @Resource
    private Response response;




    /**
     * create by: leigq
     * description: 登录
     * create time: 2019/6/28 17:11
     *
     * @return 登录结果
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Response login(AccountDTO accountDTO) {
        log.warn("进入登录.....");

        String userName = accountDTO.getOpenCode();
        String password = accountDTO.getPassword();

        if (StringUtils.isBlank(userName)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = iAccountService.login(userName, password);
        // 登录成功返回用户信息
        if (loginUser.getToken() != null && loginUser.getToken().trim().length() > 0)
            return response.success("登录成功！", loginUser);
        else
            return response.failure("登录失败",loginUser);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Response register(AccountDTO accountDTO){
        String password = accountDTO.getPassword();
        Integer flag = iAccountService.register(accountDTO);
        if(flag == 1){
            CacheUser loginUser = iAccountService.login(accountDTO.getOpenCode(), password);
            return response.success("注册成功，已自动登录！", loginUser);
        }else if (flag == -1){
            response.failure("用户名为空！");
        }else if (flag == -2){
            response.failure("密码为空！");
        }else if (flag == -3){
            response.failure("昵称能为空!");
        }
            return response.failure("注册失败！");
    }

    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 17:37
     */
    @ApiIgnore
    @RequestMapping("/logout")
    public Response logOut() {
        iAccountService.logout();
        return response.success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return  
     */
    @ApiIgnore
    @RequestMapping("/un_auth")
    public Response unAuth() {
        return response.failure(HttpStatus.UNAUTHORIZED, "用户未登录！", null);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/7/3 14:53
     * @return
     */
    @ApiIgnore
    @RequestMapping("/unauthorized")
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }
}
