package com.hrm.social_security.service;

import com.hrm.model.social_security.CompanySettings;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:53
 * @Description: 社保企业设置信息业务接口
 */
public interface CompanySettingsService {
    /**
     * 根据企业id查询企业公积金设置信息
     *
     * @param companyId
     * @return
     */
    CompanySettings findById(String companyId);

    /**
     * 制作新报表 切换统计周期
     *
     * @param companyId
     * @param yearMonth
     */
    void updateSettings(String companyId, String yearMonth);

    /**
     * 保存企业设置
     *
     * @param companyId
     * @param companySettings
     */
    void saveSettings(String companyId, CompanySettings companySettings);
}
