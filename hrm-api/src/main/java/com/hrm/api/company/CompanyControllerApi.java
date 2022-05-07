package com.hrm.api.company;

import com.hrm.core.pojo.Result;
import com.hrm.model.company.Company;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:36
 * @Description: 企业信息api
 */
@RequestMapping("/company")
public interface CompanyControllerApi {
    /**
     * 保存企业信息
     *
     * @param company
     * @return
     */
    @PostMapping
    Result<Object> save(@RequestBody Company company);

    /**
     * 删除企业信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    Result<Object> delete(@PathVariable("id") String id);

    /**
     * 修改企业信息
     *
     * @param id
     * @param company
     * @return
     */
    @PutMapping("/{id}")
    Result<Object> update(@PathVariable("id") String id, @RequestBody Company company);

    /**
     * 获取企业信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Company> get(@PathVariable("id") String id);

    /**
     * 获取企业列表
     *
     * @return
     */
    @GetMapping
    Result<List<Company>> list();
}
