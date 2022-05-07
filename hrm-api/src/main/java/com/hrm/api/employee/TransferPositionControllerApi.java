package com.hrm.api.employee;

import com.hrm.core.pojo.Result;
import com.hrm.model.employee.entity.TransferPosition;
import com.hrm.model.employee.entity.UserCompanyJobs;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:41
 * @Description: 调岗Api
 */
@RequestMapping("/employees/transferPosition")
public interface TransferPositionControllerApi {
    /**
     * 调岗表单保存
     *
     * @param userId
     * @param transferPosition
     * @return
     */
    @PostMapping("/{id}")
    Result<Object> saveTransferPosition(@PathVariable("id") String userId, @RequestBody TransferPosition transferPosition);

    /**
     * 调岗信息获取
     *
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    Result<UserCompanyJobs> get(@PathVariable("id") String userId);
}
