package com.hrm.api.company;

import com.hrm.core.entity.Result;
import com.hrm.model.company.Department;
import com.hrm.model.company.vo.CompanyDepartmentListVo;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:46
 * @Description: 部门Api
 */
@RequestMapping("/company/department")
public interface DepartmentControllerApi {
    /**
     * 保存部门信息
     *
     * @param department
     * @return
     */
    @PostMapping
    Result<Object> save(@RequestBody Department department);

    /**
     * 获取部门列表
     *
     * @return
     */
    @GetMapping
    Result<CompanyDepartmentListVo> list();

    /**
     * 根据id获取部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Department> get(@PathVariable("id") String id);

    /**
     * 根据id修改部门信息
     *
     * @param id
     * @param department
     * @return
     */
    @PutMapping("/{id}")
    Result<Object> update(@PathVariable("id") String id, @RequestBody Department department);

    /**
     * 根据部门id删除部门
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    Result<Object> delete(@PathVariable("ids") String... ids);

    /**
     * 根据部门编码以及企业id获取部门信息
     *
     * @param code
     * @param companyId
     * @return
     */
    @PostMapping("/search")
    Result<Department> findByCode(@RequestParam("code") String code, @RequestParam("companyId") String companyId);
}
