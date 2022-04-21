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
 * @CreateTime: Created in 2022-04-20 09:25
 * @Description: 调休配置表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_day_off_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayOffConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4769107247212400826L;

    @Id
    private String id;
    private String companyId;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 调休最后有效日期
     */
    private String latestEffectDate;
    /**
     * 调休单位
     */
    private String unit;
}