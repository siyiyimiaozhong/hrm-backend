package com.hrm.company.service;

import com.hrm.model.company.Department;
import com.hrm.model.company.vo.CompanyDepartmentListVo;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 10:53
 * @Description: 部门业务处理接口
 */
public interface DepartmentService {
    /**
     * 校验部门信息并保存
     *
     * @param department
     */
    void checkAndInsert(Department department);

    /**
     * 校验并更新部门信息
     *
     * @param department
     */
    void checkAndUpdate(String id, Department department);

    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    Department findById(String id);

    /**
     * 根据id删除部门信息
     *
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 根据企业Id获取所有部门信息
     *
     * @return
     */
    CompanyDepartmentListVo findAllByCompanyId(String companyId);

    /**
     * 根据部门编码以及企业id查询部门
     *
     * @param code
     * @param companyId
     * @return
     */
    Department findByCodeAndCompanyId(String code, String companyId);
}
