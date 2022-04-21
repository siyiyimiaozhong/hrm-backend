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
 * @CreateTime: Created in 2022-04-20 12:52
 * @Description: 加班配置表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_extra_duty_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDutyConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7803282752622736299L;

    @Id
    private String id;
    private String companyId;
    private String departmentId;

    /**
     * 每日标准工作时长，单位小时
     */
    private String workHoursDay;
    /**
     * 是否打卡0开启1关闭
     */
    private Integer isClock;
    /**
     * 是否开启加班补偿0开启1关闭
     */
    private String isCompensationint;
}
