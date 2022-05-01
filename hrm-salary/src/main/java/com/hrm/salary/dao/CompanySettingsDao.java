package com.hrm.salary.dao;

import com.hrm.model.salary.entity.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:31
 * @Description: 工资-企业设置信息表持久层接口
 */
public interface CompanySettingsDao extends JpaRepository<CompanySettings, String>, JpaSpecificationExecutor<CompanySettings> {
}