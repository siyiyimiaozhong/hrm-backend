package com.hrm.model.attendance.bo;

import com.hrm.model.attendance.entity.Attendance;
import lombok.Data;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-22 08:59
 * @Description:
 */
@Data
public class AttendanceItemBO {

    /**
     * 编号
     */
    private String	id;
    /**
     * 名称
     */
    private String	username;
    /**
     * 工号
     */
    private String	workNumber;
    /**
     * 部门
     */
    private String	departmentName;
    /**
     * 手机
     */
    private String	mobile;
    /**
     * 考勤记录
     */
    private List<Attendance> attendanceRecord ;
    /**
     * 部门ID
     */
    private String departmentId;
}