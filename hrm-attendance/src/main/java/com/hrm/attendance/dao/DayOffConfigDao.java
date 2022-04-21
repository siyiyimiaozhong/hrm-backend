package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.DayOffConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:21
 * @Description: 调休配置持久层接口
 */
public interface DayOffConfigDao extends CrudRepository<DayOffConfig, Long>, JpaRepository<DayOffConfig, Long>, JpaSpecificationExecutor<DayOffConfig> {

    /**
     * 根据公司和部门查询扣款配置信息
     *
     * @param companyId
     * @param departmentId
     * @return
     */
    DayOffConfig findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}

