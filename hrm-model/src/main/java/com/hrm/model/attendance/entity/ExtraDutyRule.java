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
 * @CreateTime: Created in 2022-04-20 12:53
 * @Description: 加班规则表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "atte_extra_duty_rule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraDutyRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3184878395170734186L;

    @Id
    private String id;
    private String extraDutyConfigId;
    private String companyId;
    private String departmentId;

    /**
     * 规则内容
     */
    private String rule;
    private String ruleStartTime;
    private String ruleEndTime;

    /**
     * 是否调休0不调休1调休
     */
    private Integer isTimeOff;

    /**
     * 是否可用 0开启 1 关闭
     */
    private Integer isEnable;
}
