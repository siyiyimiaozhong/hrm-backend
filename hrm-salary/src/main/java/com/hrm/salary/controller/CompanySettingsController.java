package com.hrm.salary.controller;

import com.hrm.api.salary.CompanySettingsControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.CompanySettings;
import com.hrm.salary.service.CompanySettingsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 07:36
 * @Description: 企业设置控制器
 */
@CrossOrigin
@RestController
public class CompanySettingsController extends BaseController implements CompanySettingsControllerApi {

    private final CompanySettingsService companySettingsService;

    public CompanySettingsController(CompanySettingsService companySettingsService) {
        this.companySettingsService = companySettingsService;
    }

    @Override
    public Result<CompanySettings> getCompanySettings() {
        CompanySettings companySettings = companySettingsService.findById(super.companyId);
        return Result.success(companySettings);
    }

    @Override
    public Result<Object> saveCompanySettings(CompanySettings companySettings) {
        companySettings.setCompanyId(companyId);
        companySettingsService.save(companySettings);
        return Result.success();
    }

    @Override
    public Result<Object> newReport(@PathVariable(value = "yearMonth") String yearMonth) {
        CompanySettings companySettings = new CompanySettings();
        companySettings.setCompanyId(companyId);
        companySettings.setDataMonth(yearMonth);
        companySettingsService.save(companySettings);
        return Result.success();
    }
}
