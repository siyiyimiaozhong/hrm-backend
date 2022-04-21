package com.hrm.social_security.dao;

import com.hrm.model.social_security.entity.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:48
 * @Description: 社保-企业设置信息持久层接口
 */
public interface CompanySettingsDao extends JpaRepository<CompanySettings, String>, JpaSpecificationExecutor<CompanySettings> {
}