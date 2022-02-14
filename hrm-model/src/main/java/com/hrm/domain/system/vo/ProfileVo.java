package com.hrm.domain.system.vo;

import com.hrm.domain.system.Permission;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.User;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
     * 角色权限信息
     */
    private Map<String, Object> roles;

    public ProfileVo(User user) {
        this.mobile = user.getMobile();
        this.username = user.getUsername();
        this.company = user.getCompanyName();

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
