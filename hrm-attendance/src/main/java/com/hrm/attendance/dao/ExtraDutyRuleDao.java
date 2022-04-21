package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.ExtraDutyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:25
 * @Description: 加班规则持久层接口
 */
public interface ExtraDutyRuleDao extends JpaRepository<ExtraDutyRule, String>, JpaSpecificationExecutor<ExtraDutyRule> {

    /**
     * 根据公司和部门查询加班规则配置信息
     *
     * @param extraDutyConfigId
     * @return
     */
    List<ExtraDutyRule> findByExtraDutyConfigId(String extraDutyConfigId);


    /**
     * 删除配置id的规则
     *
     * @param extraDutyConfigId
     * @return
     */
    Integer deleteByExtraDutyConfigId(String extraDutyConfigId);


    /**
     * @param companyId
     * @param departmentId
     * @return
     */
    List<ExtraDutyRule> findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}
