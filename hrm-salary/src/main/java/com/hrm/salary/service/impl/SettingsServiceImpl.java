package com.hrm.salary.service.impl;

import com.hrm.model.salary.entity.Settings;
import com.hrm.salary.dao.SettingsDao;
import com.hrm.salary.service.SettingsService;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:57
 * @Description: 福利津贴业务层实现类
 */
@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsDao settingsDao;

    public SettingsServiceImpl(SettingsDao settingsDao) {
        this.settingsDao = settingsDao;
    }

    @Override
    public Settings findById(String companyId) {
        return this.settingsDao.getOne(companyId);
    }

    @Override
    public void save(Settings settings) {
        this.settingsDao.save(settings);
    }
}
