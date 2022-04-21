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
 * @CreateTime: Created in 2022-03-18 07:38
 * @Description: 月度员工归档实体类
 */
@Entity
@Table(name = "em_archive")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archive implements Serializable {
    private static final long serialVersionUID = 5768915936056289957L;
    /**
     * ID
     */
    @Id
    private String id;
    /**
     * 操作人
     */
    private String opUser;
    /**
     * 月份
     */
    private String month;
    /**
     * 企业ID
     */
    private String companyId;
    /**
     * 总人数
     */
    private Integer totals;
    /**
     * 在职人数
     */
    private Integer payrolls;
    /**
     * 离职人数
     */
    private Integer departures;
    /**
     * 数据
     */
    private String data;
    /**
     * 创建时间
     */
    private Date createTime;
}
