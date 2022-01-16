package com.hrm.company.service;

import com.hrm.domain.company.Company;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-14 7:29
 * @Description: company业务层接口
 */
public interface CompanyService {
    /**
     * 保存企业信息
     * @param company
     */
    void save(Company company);

    /**
     * 更新企业信息
     * @param id
     * @param company
     */
    void update(Long id, Company company);

    /**
     * 删除企业信息
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查询企业信息
     * @param id
     * @return 企业信息
     */
    Company get(Long id);

    /**
     * 查询企业列表
     * @return 企业列表
     */
    List<Company> findAll();
}
