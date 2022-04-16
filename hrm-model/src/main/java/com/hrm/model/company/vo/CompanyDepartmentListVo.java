package com.hrm.model.company.vo;

import com.hrm.model.company.Company;
import com.hrm.model.company.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 11:26
 * @Description: 企业对应部门列表VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDepartmentListVo implements Serializable {
    private static final long serialVersionUID = -5817410969422851167L;
    /**
     * 企业id
     */
    private String companyId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业联系人
     */
    private String companyManage;
    /**
     * 企业部门列表
     */
    private List<Department> depts;

    public CompanyDepartmentListVo(Company company, List<Department> departments) {
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();
        this.depts = departments;
    }
}
