package com.hrm.employee.service;

import com.hrm.model.employee.entity.Resignation;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:50
 * @Description: 离职申请业务层接口
 */
public interface ResignationService {
    /**
     * 保存离职申请
     *
     * @param userId
     * @param resignation
     */
    void save(String userId, Resignation resignation);

    /**
     * 获取离职信息
     *
     * @param userId
     * @return
     */
    Resignation get(String userId);
}
