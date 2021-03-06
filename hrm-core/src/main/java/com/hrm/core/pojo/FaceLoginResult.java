package com.hrm.core.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 11:31
 * @Description: 人脸识别登录结果实体类
 */
@Data
@NoArgsConstructor
@ToString
public class FaceLoginResult implements Serializable {
    private static final long serialVersionUID = -1616426041373762391L;
    /**
     * 二维码使用状态
     * -1:未使用
     * 0：失败
     * 1：登录成功（返回用户id和token）
     */
    private String state;
    /**
     * 登录信息
     */
    private String token;
    /**
     * 用户ID
     */
    private String userId;

    public FaceLoginResult(String state, String token, String userId) {
        this.state = state;
        this.token = token;
        this.userId = userId;
    }

    public FaceLoginResult(String state) {
        this.state = state;
    }
}