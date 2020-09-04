package pren.zhl.tool.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Account;
import pren.zhl.tool.entity.Permission;
import pren.zhl.tool.entity.Role;
import pren.zhl.tool.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Utils {

    public List<Permission> getAppMenu(List<Permission> appMenus, List<Permission> back) {
        if (appMenus.size() <= 0) {
            // 菜单加入完毕，退出
            return null;
        } else if (back.size() <= 0) {
            // 菜单刚刚开始加入，先找出顶级菜单
            for (int i = 0; i < appMenus.size(); i++) {
                if (appMenus.get(i).getParentId() == 0) {
                    back.add(appMenus.get(i));
                    appMenus.remove(appMenus.get(i--));
                    continue;
                }
            }
            // 没找到顶级菜单，直接退出
            if (back.size() <= 0) {
                return null;
            }
            // 存在顶级菜单，递归调用，找出下级菜单
            getAppMenu(appMenus, back);
        } else {
            // 加入菜单

            for (int i = 0; i < appMenus.size(); i++) {
                for (int j = 0; j < back.size(); j++) {
                    if (appMenus.get(i).getParentId() == back.get(j).getId()) {

                        // 判断当前菜单是否存在子菜单
                        if (back.get(j).getSubAppMenu() == null) {
                            List<Permission> appSubMenus = new ArrayList<Permission>();
                            appSubMenus.add(appMenus.get(i));
                            back.get(j).setSubAppMenu(appSubMenus);
                        } else {
                            back.get(j).getSubAppMenu().add(appMenus.get(i));
                        }

                        appMenus.remove(appMenus.get(i--));
                        // 下级菜单
                        getAppMenu(appMenus, back.get(j).getSubAppMenu());
                        break;
                    }
                }
            }
        }
        return back;
    }

    public  AccountDTO CurrUser(){
        AccountDTO accountDTO = new AccountDTO();
        Object object = SecurityUtils.getSubject().getPrincipal();
        if (object != null){
            BeanUtils.copyProperties(object,accountDTO);
        }
        return  accountDTO;
    }

    public List<AccountDTO> getAccountsList(List<AccountDTO> accountDTOList){
        if (accountDTOList.size() == 0)
            return null;
        for (AccountDTO accountDTO : accountDTOList) {
            try {
                String[] ids = accountDTO.getIds().split("\\|\\|");
                String[] usernames = accountDTO.getUsernames().split("\\|\\|");
                ArrayList<Account> list = new ArrayList<Account>();
                int idCount = ids.length;
                int usersCount = usernames.length;
                if (idCount == usersCount){
                    for (int i = 0; i < idCount; i++) {
                        Account account = new Account();
                        account.setOpenCode(usernames[i]);
                        try {
                            account.setId(Long.parseLong(ids[i]));
                        }catch (Exception e){
                            log.error("ids类型转换异常",ids[i]);
                        }
                        list.add(account);
                    }
                accountDTO.setOpencodes(list);
                accountDTO.setUsernames(null);
                accountDTO.setIds(null);
                }else{
                    log.error("getAccountsList/accountDTOList/ids和usernames数量不一致",accountDTOList);
                    return  null;
                }
            }catch (Exception e){
                log.error("accountDTOList组装失败",e);
                return null;
            }
        }
        return accountDTOList;
    }

    public List<Role> getRoleMenu(List<Role> roles, List<Role> back){
        if (roles.size() <= 0) {
        // 菜单加入完毕，退出
        return null;
        } else if (back.size() <= 0) {
            // 菜单刚刚开始加入，先找出顶级菜单
            for (int i = 0; i < roles.size(); i++) {
                if (roles.get(i).getParentId() == 0) {
                    back.add(roles.get(i));
                    roles.remove(roles.get(i--));
                    continue;
                }
            }
            // 没找到顶级菜单，直接退出
            if (back.size() <= 0) {
                return null;
            }
            // 存在顶级菜单，递归调用，找出下级菜单
            getRoleMenu(roles, back);
        } else {
            // 加入菜单

            for (int i = 0; i < roles.size(); i++) {
                for (int j = 0; j < back.size(); j++) {
                    if (roles.get(i).getParentId() == back.get(j).getId()) {

                        // 判断当前菜单是否存在子菜单
                        if (back.get(j).getSubRoles() == null) {
                            List<Role> subRoles = new ArrayList<Role>();
                            subRoles.add(roles.get(i));
                            back.get(j).setSubRoles(subRoles);
                        } else {
                            back.get(j).getSubRoles().add(roles.get(i));
                        }

                        roles.remove(roles.get(i--));
                        // 下级菜单
                        getRoleMenu(roles, back.get(j).getSubRoles());
                        break;
                    }
                }
            }
        }
            return back;
    }
    public List<UserRole> getUserRoleBatchList(Long userId,Long[] roles){
        List<UserRole> list = new ArrayList<UserRole>();
        for (int i = 0; i < roles.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roles[i]);
            list.add(userRole);
        }
        return list;
    }
    public List<Long> getIdlist(Long[] ids){
        List<Long> list = new ArrayList<Long>();
        for (Long id: ids) {
            list.add(id);
        }
        return list;
    }
}
