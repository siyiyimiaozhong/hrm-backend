package com.hrm.system.shiro.realm;

import com.hrm.common.shiro.realm.HrmRealm;
import com.hrm.core.constant.UserConstant;
import com.hrm.model.system.entity.Permission;
import com.hrm.model.system.entity.User;
import com.hrm.model.system.vo.ProfileVo;
import com.hrm.system.service.PermissionService;
import com.hrm.system.service.UserService;
import org.apache.shiro.authc.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-14 21:57
 * @Description: 用户realm，实现认证逻辑
 */
public class UserRealm extends HrmRealm {

    private final UserService userService;
    private final PermissionService permissionService;

    public UserRealm(UserService userService, PermissionService permissionService) {
        this.userService = userService;
        this.permissionService = permissionService;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取用户的手机号和密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String mobile = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //2.根据手机号查询用户
        User user = this.userService.findByMobile(mobile);
        //3.判断用户是否存在，用户密码是否和输入密码一致
        if (user != null && user.getPassword().equals(password)) {
            //4.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
            ProfileVo result = null;
            if (UserConstant.USER.equals(user.getLevel())) {
                result = new ProfileVo(user);
            } else {
                Map<String, Object> map = new HashMap<>();
                if (UserConstant.CO_ADMIN.equals(user.getLevel())) {
                    map.put("enVisible", "1");
                }
                List<Permission> list = permissionService.findAll(map);
                result = new ProfileVo(user, list);
            }
            //构造方法：安全数据，密码，realm域名
            return new SimpleAuthenticationInfo(result, user.getPassword(), this.getName());
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }
}

