package com.hrm.salary.service.impl;

import com.hrm.model.salary.entity.CompanySettings;
import com.hrm.salary.dao.CompanySettingsDao;
import com.hrm.salary.service.CompanySettingsService;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:52
 * @Description: 薪酬-企业设置信息业务层实现类
 */
@Service
public class CompanySettingsServiceImpl implements CompanySettingsService {

    private final CompanySettingsDao companySettingsDao;

    public CompanySettingsServiceImpl(CompanySettingsDao companySettingsDao) {
        this.companySettingsDao = companySettingsDao;
    }

    @Override
    public CompanySettings findById(String companyId) {
        return this.companySettingsDao.findById(companyId).orElse(null);
    }

    @Override
    public void save(CompanySettings companySettings) {
        companySettings.setIsSettings(1);
        this.companySettingsDao.save(companySettings);
    }
}
