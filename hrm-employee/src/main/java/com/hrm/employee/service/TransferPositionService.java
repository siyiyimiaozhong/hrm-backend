package com.hrm.employee.service;

import com.hrm.model.employee.TransferPosition;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:51
 * @Description: 员工调岗申请业务层接口
 */
public interface TransferPositionService {
    /**
     * 调岗信息保存
     *
     * @param userId
     * @param transferPosition
     */
    void save(String userId, TransferPosition transferPosition);
}
