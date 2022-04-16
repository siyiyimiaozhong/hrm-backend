package com.hrm.company.dao;

import com.hrm.model.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 10:55
 * @Description: 部门相关数据持久层接口
 */
public interface DepartmentDao extends JpaRepository<Department, String>, JpaSpecificationExecutor<Department> {

    Department findByCodeAndCompanyId(String code, String companyId);
}
