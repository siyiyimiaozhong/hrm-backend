package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.AttendanceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:19
 * @Description: 考勤配置持久层接口
 */
public interface AttendanceConfigDao extends CrudRepository<AttendanceConfig, Long>, JpaRepository<AttendanceConfig, Long>, JpaSpecificationExecutor<AttendanceConfig> {

    /**
     * 根据公司和部门查询考勤配置信息
     * @param companyId
     * @param departmentId
     * @return
     */
    AttendanceConfig findByCompanyIdAndDepartmentId(String companyId, String departmentId);
}
