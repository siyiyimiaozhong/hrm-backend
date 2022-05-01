package com.hrm.model.salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:50
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSalaryVo implements Serializable {
    private static final long serialVersionUID = -4263180665840088999L;
    //ID
    private String id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 入职时间
     */
    private String timeOfEntry;
    /**
     * 岗位
     */
    private String post;
    /**
     * 当前基本工资
     */
    private BigDecimal currentBasicSalary;
    /**
     * 当前岗位工资
     */
    private BigDecimal currentPostWage;
}
