package com.hrm.employee.controller;

import com.hrm.api.employee.JobsControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.entity.Result;
import com.hrm.employee.service.UserCompanyJobsService;
import com.hrm.model.employee.UserCompanyJobs;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 11:23
 * @Description: 员工岗位信息控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/employees/jobs")
public class JobsController extends BaseController implements JobsControllerApi {

    private final UserCompanyJobsService userCompanyJobsService;

    public JobsController(UserCompanyJobsService userCompanyJobsService) {
        this.userCompanyJobsService = userCompanyJobsService;
    }

    /**
     * 员工岗位信息保存
     */
    @PutMapping("/{id}")
    @Override
    public Result<Object> saveJobsInfo(@PathVariable("id") String userId, @RequestBody UserCompanyJobs sourceInfo) {
        this.userCompanyJobsService.save(userId, super.companyId, sourceInfo);
        return Result.success();
    }

    /**
     * 员工岗位信息读取
     */
    @GetMapping("/{id}")
    @Override
    public Result<UserCompanyJobs> get(@PathVariable("id") String userId) {
        UserCompanyJobs info = userCompanyJobsService.get(userId);
        return Result.success(info);
    }
}
