package com.hrm.salary.controller;

import com.hrm.api.salary.SalaryControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.salary.vo.UserSalaryItemVo;
import com.hrm.salary.service.SalaryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 07:39
 * @Description: 薪酬控制器
 */
@CrossOrigin
@RestController
public class SalaryController extends BaseController implements SalaryControllerApi {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @Override
    public Result<UserSalary> modifyGet(@PathVariable(value = "userId") String userId) {
        UserSalary userSalary = this.salaryService.findUserSalary(userId);
        return Result.success(userSalary);
    }

    @Override
    public Result<Object> modify(@PathVariable("userId") String userId, @RequestBody UserSalary userSalary) {
        userSalary.setUserId(userId);
        this.salaryService.saveUserSalary(userSalary);
        return Result.success();
    }

    @Override
    public Result<Object> init(@RequestBody UserSalary userSalary) {
        userSalary.setFixedBasicSalary(userSalary.getCurrentBasicSalary());
        userSalary.setFixedPostWage(userSalary.getCurrentPostWage());
        this.salaryService.saveUserSalary(userSalary);
        return Result.success();
    }

    @Override
    public Result<PageResult<UserSalaryItemVo>> list(@RequestBody Map<String, Object> map) {
        //1.获取请求参数,page,size
        Integer page = (Integer) map.get("page");
        Integer pageSize = (Integer) map.get("pageSize");
        //2.调用service查询
        PageResult<UserSalaryItemVo> pr = salaryService.findAll(page, pageSize, companyId);
        return Result.success(pr);
    }
}
