package com.hrm.model.attendance.entity;

import com.hrm.core.template.AttendanceTemplate;
import com.hrm.model.attendance.base.BaseEntity;
import com.hrm.model.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 09:12
 * @Description: 考勤表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 594829320797158219L;

    @Id
    private String id;
    private String companyId;
    private String departmentId;

    private String userId;
    private Integer adtStatu;
    private long jobStatu;

    private Date adtInTime;
    private String adtInPlace;
    private String adtInHourse;

    private String adtInCoordinate;
    private Date adtOutTime;
    private String adtOutPlace;
    private String adtOutHourse;
    private String day; //考勤日期

    public Attendance(AttendanceTemplate template, User user) {
        this.adtInTime = template.getInTime();
        this.adtOutTime = template.getOutTime();
        this.userId = user.getId();
        this.companyId = user.getCompanyId();
        this.departmentId = user.getDepartmentId();
        this.jobStatu = user.getInServiceStatus();
    }
}
