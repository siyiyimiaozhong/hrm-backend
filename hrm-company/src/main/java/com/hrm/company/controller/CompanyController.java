package com.hrm.company.controller;

import com.hrm.common.entity.Result;
import com.hrm.company.service.CompanyService;
import com.hrm.domain.company.Company;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-16 17:11
 * @Description: 企业信息控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public Result<Object> save(@RequestBody Company company) {
        this.companyService.save(company);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable("id") String id) {
        this.companyService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Object> update(@PathVariable("id") String id, @RequestBody Company company) {
        this.companyService.update(id, company);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Company> get(@PathVariable("id") String id) {
        Company company = this.companyService.get(id);
        return Result.success(company);
    }

    @GetMapping
    public Result<List<Company>> page() {
        List<Company> all = this.companyService.findAll();
        return Result.success(all);
    }
}
