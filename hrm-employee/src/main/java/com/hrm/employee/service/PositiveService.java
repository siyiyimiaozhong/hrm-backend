package com.hrm.employee.service;

import com.hrm.model.employee.Positive;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:50
 * @Description: 转正申请业务层接口
 */
public interface PositiveService {
    /**
     * 保存转正申请信息
     *
     * @param userId
     * @param positive
     */
    void save(String userId, Positive positive);

    /**
     * 获取转正申请
     *
     * @param uid
     * @return
     */
    Positive get(String uid);
}
