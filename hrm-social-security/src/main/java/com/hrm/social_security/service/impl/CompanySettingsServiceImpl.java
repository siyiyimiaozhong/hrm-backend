package com.hrm.social_security.service.impl;

import com.hrm.model.social_security.CompanySettings;
import com.hrm.social_security.dao.CompanySettingsDao;
import com.hrm.social_security.service.CompanySettingsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:56
 * @Description: 社保企业设置信息业务层实现类
 */
@Service
public class CompanySettingsServiceImpl implements CompanySettingsService {

    private final CompanySettingsDao companySettingsDao;

    public CompanySettingsServiceImpl(CompanySettingsDao companySettingsDao) {
        this.companySettingsDao = companySettingsDao;
    }

    @Override
    public CompanySettings findById(String companyId) {
        Optional<CompanySettings> optional = companySettingsDao.findById(companyId);
        return optional.orElse(null);
    }

    @Override
    public void updateSettings(String companyId, String yearMonth) {
        //修改企业设置
        CompanySettings cs = this.findById(companyId);
        if (cs == null) {
            cs = new CompanySettings();
        }
        cs.setCompanyId(companyId);
        cs.setDataMonth(yearMonth);
        cs.setIsSettings(1);//已经完成当月设置
        this.companySettingsDao.save(cs);
    }

    @Override
    public void saveSettings(String companyId, CompanySettings companySettings) {
        companySettings.setCompanyId(companyId);
        companySettings.setIsSettings(1);//已经完成当月设置
        this.companySettingsDao.save(companySettings);
    }
}
