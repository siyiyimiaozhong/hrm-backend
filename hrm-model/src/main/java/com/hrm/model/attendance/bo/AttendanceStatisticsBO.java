package com.hrm.model.attendance.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-22 09:02
 * @Description: 考勤统计结果
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceStatisticsBO implements Serializable {

    private static final long serialVersionUID = 1160880042065774129L;

    @Id
    private String id;

    private String day;

    private Integer adtStatu;
}