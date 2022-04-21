package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.LeaveConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:25
 * @Description: 请假配置持久层接口
 */
public interface LeaveConfigDao extends CrudRepository<LeaveConfig, Long>, JpaRepository<LeaveConfig, Long>, JpaSpecificationExecutor<LeaveConfig> {

    /**
     * 根据公司和部门查询考请假配置信息
     *
     * @param companyId
     * @param departmentId
     * @return
     */
    List<LeaveConfig> findByCompanyIdAndDepartmentId(String companyId, String departmentId);

    /**
     * 根据公司、部门和请假类型查询考请假配置信息
     *
     * @param companyId
     * @param departmentId
     * @param leaveType
     * @return
     */
    LeaveConfig findByCompanyIdAndDepartmentIdAndLeaveType(String companyId, String departmentId, String leaveType);
}