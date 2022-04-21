package com.hrm.model.attendance.entity;

import com.hrm.model.attendance.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-20 12:56
 * @Description: 请假配置表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_leave_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5488722149593923374L;

    @Id
    private String id;

    private String companyId;

    private String departmentId;

    /**
     * 类型
     */
    private String leaveType;

    private Integer isEnable;
}