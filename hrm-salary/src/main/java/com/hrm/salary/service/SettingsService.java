package com.hrm.salary.service;

import com.hrm.model.salary.entity.Settings;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:56
 * @Description: 福利津贴业务层接口
 */
public interface SettingsService {
    /**
     * 根据id获取企业设置
     *
     * @param companyId
     * @return
     */
    Settings findById(String companyId);

    /**
     * 保存配置
     *
     * @param settings
     */
    void save(Settings settings);
}
