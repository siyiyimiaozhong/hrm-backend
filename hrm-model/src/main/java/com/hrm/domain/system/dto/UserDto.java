package com.hrm.domain.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:31
 * @Description: 用户数据接收实体类
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -103052075692063969L;
    /**
     * 是否分配部门 0:未分配 1:已分配
     */
    private String hasDept;
    /**
     * 部门id
     */
    private String departmentId;
    /**
     * 企业id
     */
    private String companyId;

    private Integer page;

    private Integer size;
}
