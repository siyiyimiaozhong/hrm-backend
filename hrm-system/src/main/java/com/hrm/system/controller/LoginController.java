package com.hrm.system.controller;

import com.hrm.common.entity.Result;
import com.hrm.domain.system.vo.LoginVo;
import com.hrm.domain.system.vo.ProfileVo;
import com.hrm.system.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-14 07:46
 * @Description: 登录控制器
 */
@RestController
@RequestMapping("/sys")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginVo loginVo) {
        String token = this.userService.login(loginVo.getMobile(), loginVo.getPassword());
        return Result.success(token);
    }

    /**
     * 登录成功后，获取用户信息
     *
     * @return
     */
    @PostMapping("/profile")
    public Result<Object> profile(HttpServletRequest request) {
        ProfileVo profileVo = this.userService.profile(request);
        return Result.success(profileVo);
    }
}
