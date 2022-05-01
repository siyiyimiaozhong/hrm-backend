package com.hrm.api.salary;

import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.CompanySettings;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:36
 * @Description: 工资-企业设置Api接口
 */
@RequestMapping("/salarys")
public interface CompanySettingsControllerApi {

    /**
     * 获取企业是否设置工资
     *
     * @return
     */
    @GetMapping("/company-settings")
    Result<CompanySettings> getCompanySettings();

    /**
     * 保存企业工资设置
     *
     * @param companySettings
     * @return
     */
    @PostMapping("/company-settings")
    Result<Object> saveCompanySettings(@RequestBody CompanySettings companySettings);

    /**
     * 构造新报表
     *
     * @param yearMonth
     * @return
     */
    @PutMapping("/reports/{yearMonth}/newReport")
    Result<Object> newReport(@PathVariable(value = "yearMonth") String yearMonth);
}
