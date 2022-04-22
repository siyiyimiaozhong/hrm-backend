package com.hrm.attendance.service;

import com.hrm.model.attendance.entity.AttendanceConfig;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:07
 * @Description: 考勤配置业务层接口
 */
public interface AttendanceConfigService {
    AttendanceConfig getAtteConfig(String companyId, String departmentId);

    void saveAndUpdateAtteConfig(String companyId, AttendanceConfig attendanceConfig);
}
