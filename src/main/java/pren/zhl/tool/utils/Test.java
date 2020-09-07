package pren.zhl.tool.utils;

import com.alibaba.fastjson.JSON;
import pren.zhl.tool.entity.RolePermission;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[]){
        List<RolePermission> list = new ArrayList<>();
        RolePermission permission = new RolePermission();
        RolePermission permission1 = new RolePermission();
        permission.setId(1L);
        permission1.setId(2L);
        permission.setRoleId(3L);
        permission1.setRoleId(4L);
        list.add(permission);
        list.add(permission1);
        String str = JSON.toJSONString(list);
        System.out.println(str);
    }
}
