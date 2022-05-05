package com.hrm.system.controller;

import com.hrm.api.system.LoginControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.system.vo.LoginVo;
import com.hrm.model.system.vo.ProfileVo;
import com.hrm.system.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-14 07:46
 * @Description: 登录控制器
 */
@CrossOrigin
@RestController
public class LoginController extends BaseController implements LoginControllerApi {

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
    @Override
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String sessionId = this.userService.login(loginVo.getMobile(), loginVo.getPassword());
        return Result.success(sessionId);
    }

    /**
     * 登录成功后，获取用户信息
     *
     * @return
     */
    @Override
    public Result<Object> profile() {
        ProfileVo profileVo = this.userService.profile();
        return Result.success(profileVo);
    }
}
