package com.hrm.api.salary;

import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.Settings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:45
 * @Description: 工资-企业设置（奖金，津贴等）Api接口
 */
@RequestMapping("/salarys")
public interface SettingsControllerApi {
    /**
     * 获取企业计薪及津贴设置
     *
     * @return
     */
    @GetMapping("/settings")
    Result<Settings> getSettings();

    /**
     * 保存企业计薪及津贴设置
     *
     * @param settings
     * @return
     */
    @PostMapping("/settings")
    Result<Object> saveSettings(@RequestBody Settings settings);
}
