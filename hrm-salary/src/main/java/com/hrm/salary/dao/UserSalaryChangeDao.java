package com.hrm.salary.dao;

import com.hrm.model.salary.entity.UserSalaryChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:33
 * @Description: 员工调薪表持久层接口
 */
public interface UserSalaryChangeDao extends JpaRepository<UserSalaryChange, String>, JpaSpecificationExecutor<UserSalaryChange> {
}
