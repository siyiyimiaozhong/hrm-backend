package com.hrm.model.employee.entity;

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
 * @CreateTime: Created in 2022-03-18 07:39
 * @Description: 员工转正申请实体类
 */
@Entity
@Table(name = "em_positive")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Positive implements Serializable {
    private static final long serialVersionUID = 2391824518947910773L;
    /**
     * 员工ID
     */
    @Id
    private String userId;
    /**
     * 转正日期
     */
    private Date dateOfCorrection;
    /**
     * 转正评价
     */
    private String correctionEvaluation;
    /**
     * 附件
     */
    private String enclosure;
    /**
     * 单据状态 1是未执行，2是已执行
     */
    private Integer estatus;
    /**
     * 创建时间
     */
    private Date createTime;
}
