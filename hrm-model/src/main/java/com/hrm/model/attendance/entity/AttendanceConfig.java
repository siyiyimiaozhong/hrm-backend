package com.hrm.model.attendance.entity;

import com.hrm.model.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 09:13
 * @Description: 考勤配置表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_attendance_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3920867200840645678L;

    @Id
    private String id;

    private String companyId;

    @NotBlank(message = "部门ID不能为空")
    private String departmentId;

    @NotNull(message = "上午上班时间不能为空")
    private String morningStartTime;
    @NotNull(message = "上午下班时间不能为空")
    private String morningEndTime;
    @NotNull(message = "下午上班时间不能为空")
    private String afternoonStartTime;
    @NotNull(message = "下午下班时间不能为空")
    private String afternoonEndTime;
}
