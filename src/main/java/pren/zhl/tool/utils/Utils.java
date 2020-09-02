package pren.zhl.tool.utils;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import pren.zhl.tool.dto.AccountDTO;
import pren.zhl.tool.entity.Permission;

import java.util.ArrayList;
import java.util.List;

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
        BeanUtils.copyProperties(object,accountDTO);
        return accountDTO;
    }
}
