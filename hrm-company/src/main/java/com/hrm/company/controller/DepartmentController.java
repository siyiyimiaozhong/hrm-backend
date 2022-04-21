package com.hrm.company.controller;

import com.hrm.api.company.DepartmentControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.company.service.DepartmentService;
import com.hrm.core.pojo.Result;
import com.hrm.model.company.Department;
import com.hrm.model.company.vo.CompanyDepartmentListVo;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 10:52
 * @Description: 部门控制器
 */
@CrossOrigin
@RestController
public class DepartmentController extends BaseController implements DepartmentControllerApi {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 保存部门信息
     *
     * @param department
     * @return
     */
    @Override
    public Result<Object> save(@RequestBody Department department) {
        department.setCompanyId(this.companyId);
        this.departmentService.checkAndInsert(department);
        return Result.success();
    }

    /**
     * 查询企业内部所有部门信息
     *
     * @return
     */
    @Override
    public Result<CompanyDepartmentListVo> list() {
        CompanyDepartmentListVo companyDepartmentListVo = this.departmentService.findAllByCompanyId(this.companyId);
        return Result.success(companyDepartmentListVo);
    }

    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<Department> get(@PathVariable("id") String id) {
        Department department = this.departmentService.findById(id);
        return Result.success(department);
    }

    /**
     * 修改部门信息
     *
     * @param id
     * @param department
     * @return
     */
    @Override
    public Result<Object> update(@PathVariable("id") String id, @RequestBody Department department) {
        this.departmentService.checkAndUpdate(id, department);
        return Result.success();
    }

    @Override
    public Result<Object> delete(@PathVariable("ids") String... ids) {
        this.departmentService.delete(ids);
        return Result.success();
    }

    @Override
    public Result<Department> findByCode(@RequestParam("code") String code, @RequestParam("companyId") String companyId) {
        Department department = this.departmentService.findByCodeAndCompanyId(code, companyId);
        return Result.success(department);
    }


}
