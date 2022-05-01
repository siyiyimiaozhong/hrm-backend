package com.hrm.salary.service;

import com.hrm.model.salary.entity.CompanySettings;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:48
 * @Description: 企业设置信息业务层接口
 */
public interface CompanySettingsService {
    /**
     * 根据id获取查询
     *
     * @param companyId
     * @return
     */
    CompanySettings findById(String companyId);

    /**
     * 保存配置
     *
     * @param companySettings
     */
    void save(CompanySettings companySettings);
}
