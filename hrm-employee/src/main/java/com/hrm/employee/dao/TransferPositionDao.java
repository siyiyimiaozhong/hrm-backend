package com.hrm.employee.dao;

import com.hrm.model.employee.TransferPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:47
 * @Description: 员工调岗申请持久层
 */
public interface TransferPositionDao extends JpaRepository<TransferPosition, String>, JpaSpecificationExecutor<TransferPosition> {
    TransferPosition findByUserId(String uid);
}