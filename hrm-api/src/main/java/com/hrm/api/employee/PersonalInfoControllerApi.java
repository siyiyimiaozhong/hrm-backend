package com.hrm.api.employee;

import com.hrm.core.entity.Result;
import com.hrm.model.employee.UserCompanyPersonal;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:34
 * @Description: 员工个人信息Api
 */
@RequestMapping("/employees/personalInfo")
public interface PersonalInfoControllerApi {
    /**
     * 员工个人信息保存
     *
     * @param userId
     * @param userCompanyPersonal
     * @return
     */
    @PostMapping(value = "/{id}")
    Result<Object> savePersonalInfo(@PathVariable("id") String userId, @RequestBody UserCompanyPersonal userCompanyPersonal);

    /**
     * 员工个人信息读取
     *
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    Result<UserCompanyPersonal> getPersonalInfo(@PathVariable("id") String userId);
}
