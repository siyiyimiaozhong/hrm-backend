package com.hrm.model.salary.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-05 12:42
 * @Description: 用户薪资列表查询条件类
 */
@Data
public class UserSalaryItemDto implements Serializable {
    private static final long serialVersionUID = -5319668420221983402L;

    /**
     * 部门ids
     */
    private List<String> departmentChecks;
    /**
     * 聘用形式
     */
    private List<Integer> approvalsTypeChecks;
    /**
     * 员工状态
     */
    private List<Integer> approvalsStateChecks;

    private Integer page;
    private Integer pageSize;
}
