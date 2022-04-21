package com.hrm.employee.service;

import com.hrm.model.employee.entity.UserCompanyJobs;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:51
 * @Description: 员工岗位信息业务层接口
 */
public interface UserCompanyJobsService {
    /**
     * 保存员工岗位信息
     *
     * @param userId
     * @param companyId
     * @param sourceInfo
     */
    void save(String userId, String companyId, UserCompanyJobs sourceInfo);

    /**
     * 获取员工岗位信息
     *
     * @param userId
     * @return
     */
    UserCompanyJobs get(String userId);
}
