package com.hrm.employee.dao;

import com.hrm.model.employee.Resignation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:45
 * @Description: 员工离职申请持久层
 */
public interface ResignationDao extends JpaRepository<Resignation, String>, JpaSpecificationExecutor<Resignation> {
    Resignation findByUserId(String userId);
}