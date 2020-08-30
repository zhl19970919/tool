package pren.zhl.tool.controller;

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

import javax.annotation.Resource;

/**
 * @author ：leigq
 * @date ：2019/6/28 16:55
 * @description：登录Controller
 */
@Slf4j
@RestController
@RequestMapping("/")
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
        return response.success("登录成功！", loginUser);
    }

    /**
     * create by: leigq
     * description: 登出
     * create time: 2019/6/28 17:37
     */
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
    @RequestMapping("/unauthorized")
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }
}
