package com.hrm.api.company;

import com.hrm.core.entity.Result;
import com.hrm.model.company.Company;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:36
 * @Description: 企业信息api
 */
public interface CompanyControllerApi {
    /**
     * 保存企业信息
     *
     * @param company
     * @return
     */
    Result<Object> save(@RequestBody Company company);

    /**
     * 删除企业信息
     *
     * @param id
     * @return
     */
    Result<Object> delete(@PathVariable("id") String id);

    /**
     * 修改企业信息
     *
     * @param id
     * @param company
     * @return
     */
    Result<Object> update(@PathVariable("id") String id, @RequestBody Company company);

    /**
     * 获取企业信息
     *
     * @param id
     * @return
     */
    Result<Company> get(@PathVariable("id") String id);

    /**
     * 获取企业列表
     *
     * @return
     */
    Result<List<Company>> list();
}
