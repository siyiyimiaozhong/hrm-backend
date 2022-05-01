package com.hrm.model.salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:51
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryItemVo implements Serializable {
    private static final long serialVersionUID = 2270963764329229065L;
    /**
     * id
     */
    private String id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 聘用形式
     */
    private String formOfEmployment;
    /**
     * 部门
     */
    private String department;
    /**
     * 入职时间
     */
    private String timeOfEntry;
    /**
     * 工资基数
     */
    private BigDecimal wageBase;
    /**
     * 津贴方案
     */
    private String subsidyScheme;

    /**
     * 是否定薪
     */
    private Boolean isFixed;
}

