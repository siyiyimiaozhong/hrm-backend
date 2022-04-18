package com.hrm.employee.controller;

import com.hrm.api.employee.PersonalInfoControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.entity.Result;
import com.hrm.employee.service.UserCompanyPersonalService;
import com.hrm.model.employee.UserCompanyPersonal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 21:43
 * @Description: 员工个人信息控制器
 */
@CrossOrigin
@RestController
public class PersonalInfoController extends BaseController implements PersonalInfoControllerApi {

    private final UserCompanyPersonalService userCompanyPersonalService;

    public PersonalInfoController(UserCompanyPersonalService userCompanyPersonalService) {
        this.userCompanyPersonalService = userCompanyPersonalService;
    }

    /**
     * 员工个人信息保存
     *
     * @param userId
     * @param userCompanyPersonal
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> savePersonalInfo(@PathVariable("id") String userId, @RequestBody UserCompanyPersonal userCompanyPersonal) {
        this.userCompanyPersonalService.save(userId, super.companyId, userCompanyPersonal);
        return Result.success();
    }

    /**
     * 员工个人信息读取
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public Result<UserCompanyPersonal> getPersonalInfo(@PathVariable("id") String userId) {
        UserCompanyPersonal info = this.userCompanyPersonalService.get(userId);
        return Result.success(info);
    }
}
