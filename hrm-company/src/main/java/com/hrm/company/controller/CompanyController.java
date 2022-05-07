package com.hrm.company.controller;

import com.hrm.api.company.CompanyControllerApi;
import com.hrm.company.service.CompanyService;
import com.hrm.core.pojo.Result;
import com.hrm.model.company.Company;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-16 17:11
 * @Description: 企业信息控制器
 */
@CrossOrigin
@RestController
public class CompanyController implements CompanyControllerApi {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Result<Object> save(@RequestBody Company company) {
        this.companyService.save(company);
        return Result.success();
    }

    @Override
    public Result<Object> delete(@PathVariable("id") String id) {
        this.companyService.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<Object> update(@PathVariable("id") String id, @RequestBody Company company) {
        this.companyService.update(id, company);
        return Result.success();
    }

    @Override
    public Result<Company> get(@PathVariable("id") String id) {
        Company company = this.companyService.get(id);
        return Result.success(company);
    }

    @Override
    public Result<List<Company>> list() {
        List<Company> all = this.companyService.findAll();
        return Result.success(all);
    }
}
