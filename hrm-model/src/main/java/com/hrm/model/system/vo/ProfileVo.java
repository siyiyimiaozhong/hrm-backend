package com.hrm.model.system.vo;

import com.hrm.model.system.Permission;
import com.hrm.model.system.Role;
import com.hrm.model.system.User;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-15 00:57
 * @Description: 权限概述返回对象
 */
@Data
public class ProfileVo implements Serializable {

    private static final long serialVersionUID = -4598647172677961964L;

    /**
     * 手机
     */
    private String mobile;
    /**
     * 用户名
     */
    private String username;
    /**
     * 企业名称
     */
    private String company;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 角色权限信息
     */
    private Map<String, Object> roles = new HashMap<>();

    public ProfileVo(User user, List<Permission> list) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        this.companyId = user.getCompanyId();

        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Permission perm : list) {
            String code = perm.getCode();
            if (perm.getType() == 1) {
                menus.add(code);
            } else if (perm.getType() == 2) {
                points.add(code);
            } else {
                apis.add(code);
            }
        }
        this.roles.put("menus", menus);
        this.roles.put("points", points);
        this.roles.put("apis", apis);
    }

    public ProfileVo(User user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();
        this.companyId = user.getCompanyId();

        Set<Role> roles = user.getRoles();
        Set<String> menus = new HashSet<>();
        Set<String> points = new HashSet<>();
        Set<String> apis = new HashSet<>();
        for (Role role : roles) {
            Set<Permission> perms = role.getPermissions();
            for (Permission perm : perms) {
                String code = perm.getCode();
                if (perm.getType() == 1) {
                    menus.add(code);
                } else if (perm.getType() == 2) {
                    points.add(code);
                } else {
                    apis.add(code);
                }
            }
        }

        this.roles.put("menus", menus);
        this.roles.put("points", points);
        this.roles.put("apis", apis);
    }
}
