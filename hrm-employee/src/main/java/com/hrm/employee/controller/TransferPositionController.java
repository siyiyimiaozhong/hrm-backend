package com.hrm.employee.controller;

import com.hrm.api.employee.TransferPositionControllerApi;
import com.hrm.core.pojo.Result;
import com.hrm.employee.service.TransferPositionService;
import com.hrm.employee.service.UserCompanyJobsService;
import com.hrm.model.employee.entity.TransferPosition;
import com.hrm.model.employee.entity.UserCompanyJobs;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 21:46
 * @Description: 调岗控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/employees/transferPosition")
public class TransferPositionController implements TransferPositionControllerApi {

    private final TransferPositionService transferPositionService;
    private final UserCompanyJobsService userCompanyJobsService;

    public TransferPositionController(TransferPositionService transferPositionService, UserCompanyJobsService userCompanyJobsService) {
        this.transferPositionService = transferPositionService;
        this.userCompanyJobsService = userCompanyJobsService;
    }

    /**
     * 调岗表单保存
     *
     * @param userId
     * @param transferPosition
     * @return
     * @throws Exception
     */
    @PostMapping("/{id}")
    @Override
    public Result<Object> saveTransferPosition(@PathVariable("id") String userId, @RequestBody TransferPosition transferPosition) {
        this.transferPositionService.save(userId, transferPosition);
        return Result.success();
    }

    /**
     * 调岗信息获取
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    @Override
    public Result<UserCompanyJobs> get(@PathVariable("id") String userId) {
        UserCompanyJobs jobsInfo = this.userCompanyJobsService.get(userId);
        return Result.success(jobsInfo);
    }
}
