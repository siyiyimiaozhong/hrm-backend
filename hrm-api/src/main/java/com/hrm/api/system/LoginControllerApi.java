package com.hrm.api.system;

import com.hrm.core.pojo.Result;
import com.hrm.model.system.vo.LoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:44
 * @Description: 登录Api
 */
@RequestMapping("/sys")
public interface LoginControllerApi {
    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    Result<String> login(@RequestBody LoginVo loginVo);

    /**
     * 登录成功后，获取用户信息
     *
     * @return
     */
    @PostMapping("/profile")
    Result<Object> profile();
}
