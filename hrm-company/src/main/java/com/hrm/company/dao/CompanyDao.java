package com.hrm.company.dao;

import com.hrm.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-14 7:18
 * @Description:
 */
public interface CompanyDao extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {
}
