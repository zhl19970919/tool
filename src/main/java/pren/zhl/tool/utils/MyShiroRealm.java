
package pren.zhl.tool.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Lazy;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.dto.RoleDTO;
import pren.zhl.tool.entity.Permission;
import pren.zhl.tool.service.IAccountService;
import pren.zhl.tool.service.IPermissionService;
import pren.zhl.tool.service.IRoleService;
import pren.zhl.tool.service.IUserService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @author ：leigq
 * @date ：2019/6/28 16:31
 * @description：自定义 shiroRealm, 主要是重写其认证、授权
 */

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Lazy
    @Resource
    private IUserService iUserService;

    @Lazy
    @Resource
    private IAccountService iAccountService;

    @Lazy
    @Resource
    private IRoleService iRoleService;

    @Lazy
    @Resource
    private IPermissionService iPermissionService;



/**
     * create by: leigq
     * description: 授权
     * create time: 2019/7/1 10:32
     *
     * @return 权限信息，包括角色以及权限
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        AccountDTO accountDTO = (AccountDTO) principals.getPrimaryPrincipal();

        // 查询用户角色，一个用户可能有多个角色
        List<RoleDTO> roles = iRoleService.getUserRoles(accountDTO.getUserId());

        for (RoleDTO role : roles) {
            authorizationInfo.addRole(role.getName());
            // 根据角色查询权限
            List<Permission> permissions = iPermissionService.getRolePermissions(role.getRoleId());
            //List<Permission> treePermission = new ArrayList<Permission>();
            //treePermission = new Utils().getAppMenu(permissions,treePermission);
            for (Permission p : permissions) {
                authorizationInfo.addStringPermission(p.getIntro());
            }
        }
        return authorizationInfo;
    }


/**
     * create by: leigq
     * description: 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * create time: 2019/7/1 09:04
     *
     * @return 身份验证信息
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("开始进行身份认证......");

        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        AccountDTO accountDTO =  iAccountService.findByOpencode(userName,null,null);
        if (Objects.isNull(accountDTO)) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                accountDTO,
                // 密码
                accountDTO.getPassword(),
                // salt = username + salt
                //ByteSource.Util.bytes(accountDTO.getCredentialsSalt()),
                ByteSource.Util.bytes(accountDTO.getSalt()),
                // realm name
                getName()
        );
    }

}


