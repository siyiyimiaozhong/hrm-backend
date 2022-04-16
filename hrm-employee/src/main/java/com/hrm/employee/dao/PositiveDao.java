package com.hrm.employee.dao;

import com.hrm.model.employee.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:46
 * @Description: 转正申请持久层
 */
public interface PositiveDao extends JpaRepository<Positive, String>, JpaSpecificationExecutor<Positive> {
    Positive findByUserId(String uid);
}