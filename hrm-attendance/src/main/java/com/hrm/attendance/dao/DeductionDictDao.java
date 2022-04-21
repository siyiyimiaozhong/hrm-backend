package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.DeductionDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:22
 * @Description: 扣款字典表持久层接口
 */
public interface DeductionDictDao extends JpaRepository<DeductionDict, String>, JpaSpecificationExecutor<DeductionDict> {

    /**
     * 根据公司和部门查询扣款配置信息
     *
     * @param companyId
     * @param departmentId
     * @return *
     */
    List<DeductionDict> findByCompanyIdAndDepartmentId(String companyId, String departmentId);

    /**
     * 查询扣款配置
     *
     * @param companyId
     * @param departmentId
     * @param dedTypeCode
     * @return
     */
    DeductionDict findByCompanyIdAndDepartmentIdAndDedTypeCode(String companyId, String departmentId, String dedTypeCode);
}
