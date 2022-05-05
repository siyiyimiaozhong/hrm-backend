package com.hrm.model.attendance.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-05 09:16
 * @Description: 考勤查询dto
 */
@Data
public class AttendanceDto implements Serializable {
    private static final long serialVersionUID = 5264903771736132981L;

    /**
     * 考勤状态
     */
    private String stateId;
    /**
     * 部门ids
     */
    private List<String> deptIds;

    private Integer page;
    private Integer pageSize;
}
