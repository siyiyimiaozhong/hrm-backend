package com.hrm.domain.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-14 07:58
 * @Description: 登录接收类
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 8431165845060822089L;

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
}
