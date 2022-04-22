package com.hrm.model.attendance.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-22 09:05
 * @Description:
 */
@Data
public class AttendanceTotalsBO implements Serializable {
    private static final long serialVersionUID = 100314180696381600L;

    /**
     * 待处理审批数量
     */
    private Integer tobeTaskCount;

    /**
     * 当前报表月份
     */
    private Integer monthOfReport;
}
