package com.hrm.api.employee;

import com.hrm.core.entity.Result;
import com.hrm.model.employee.UserCompanyJobs;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:27
 * @Description: 员工岗位信息Api
 */
public interface JobsControllerApi {
    /**
     * 员工岗位信息保存
     *
     * @param userId
     * @param sourceInfo
     * @return
     */
    Result<Object> saveJobsInfo(@PathVariable("id") String userId, @RequestBody UserCompanyJobs sourceInfo);

    /**
     * 员工岗位信息读取
     *
     * @param userId
     * @return
     */
    Result<UserCompanyJobs> get(@PathVariable("id") String userId);
}
