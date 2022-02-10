package com.hrm.system.controller;

import com.hrm.system.service.PermissionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 07:28
 * @Description: 权限控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/perm")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


}
