package com.hrm.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:40
 * @Description: 员工离职申请实体类
 */
@Entity
@Table(name = "em_resignation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resignation implements Serializable {
    private static final long serialVersionUID = 2890789302883962744L;
    /**
     * 员工Id
     */
    @Id
    private String userId;
    /**
     * 离职时间
     */
    private String resignationTime;
    /**
     * 离职类型
     */
    private String typeOfTurnover;
    /**
     * 申请离职原因
     */
    private String reasonsForLeaving;
    /**
     * 补偿金
     */
    private String compensation;
    /**
     * 代通知金
     */
    private String notifications;
    /**
     * 社保减员月
     */
    private String socialSecurityReductionMonth;
    /**
     * 公积金减员月
     */
    private String providentFundReductionMonth;
    /**
     * 图片
     */
    private String picture;
    /**
     * 创建时间
     */
    private Date createTime;
}
