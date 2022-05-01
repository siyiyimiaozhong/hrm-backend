package com.hrm.salary.controller;

import com.hrm.api.salary.SettingsControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.Settings;
import com.hrm.salary.service.SettingsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 07:43
 * @Description: 福利，津贴控制器
 */
@CrossOrigin
@RestController
public class SettingsController extends BaseController implements SettingsControllerApi {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Override
    public Result<Settings> getSettings() {
        Settings settings = settingsService.findById(companyId);
        return Result.success(settings);
    }

    @Override
    public Result<Object> saveSettings(@RequestBody Settings settings) {
        settings.setCompanyId(companyId);
        this.settingsService.save(settings);
        return Result.success();
    }
}
