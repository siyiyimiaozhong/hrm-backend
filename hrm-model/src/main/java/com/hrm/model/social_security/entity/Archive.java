package com.hrm.model.social_security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:01
 * @Description: 社保归档表实体类
 */
@Entity
@Table(name = "ss_archive")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archive implements Serializable {
    private static final long serialVersionUID = -7883369133555989567L;

    @Id
    private String id;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 年月
     */
    private String yearsMonth;
    /**
     * 创建时间
     */
    private Date creationTime;
    /**
     * 企业缴费
     */
    private BigDecimal enterprisePayment;
    /**
     * 个人缴费
     */
    private BigDecimal personalPayment;
    /**
     * 合计
     */
    private BigDecimal total;

    public Archive(String companyId, String yearMonth) {
        this.companyId = companyId;
        this.yearsMonth = yearMonth;
    }
}
