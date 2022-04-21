package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:21
 * @Description: 考勤-企业设置信息持久层接口
 */
public interface CompanySettingsDao extends JpaRepository<CompanySettings, String>, JpaSpecificationExecutor<CompanySettings> {
}
