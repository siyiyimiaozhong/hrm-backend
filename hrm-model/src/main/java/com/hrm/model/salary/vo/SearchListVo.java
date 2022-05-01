package com.hrm.model.salary.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchListVo implements Serializable {
    private static final long serialVersionUID = -8247920133289436000L;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 页尺寸
     */
    private Integer pageSize;
    /**
     * 选中部门列表
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
}
