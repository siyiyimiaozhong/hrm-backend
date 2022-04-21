package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.ExtraDutyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:24
 * @Description: 加班配置持久层接口
 */
public interface ExtraDutyConfigDao extends JpaRepository<ExtraDutyConfig, Long>, JpaSpecificationExecutor<ExtraDutyConfig> {

    /**
     * 根据公司和部门查询加班配置信息
     *
     * @param companyId
     * @param departmentId
     * @return
     */
    ExtraDutyConfig findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}
