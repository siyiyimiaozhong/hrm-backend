package com.hrm.api.system;

import com.hrm.core.pojo.Result;
import com.hrm.model.system.vo.LoginVo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:44
 * @Description: 登录Api
 */
public interface LoginControllerApi {
    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    Result<Object> login(@RequestBody LoginVo loginVo);

    /**
     * 登录成功后，获取用户信息
     *
     * @return
     */
    Result<Object> profile();
}
