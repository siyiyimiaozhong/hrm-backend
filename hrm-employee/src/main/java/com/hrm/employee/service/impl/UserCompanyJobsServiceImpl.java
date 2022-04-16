package com.hrm.employee.service.impl;

import com.hrm.core.constant.ResultCode;
import com.hrm.core.exception.BusinessException;
import com.hrm.employee.dao.UserCompanyJobsDao;
import com.hrm.employee.service.UserCompanyJobsService;
import com.hrm.model.employee.UserCompanyJobs;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 14:57
 * @Description: 员工岗位信息业务层实现类
 */
@Service
public class UserCompanyJobsServiceImpl implements UserCompanyJobsService {

    private final UserCompanyJobsDao userCompanyJobsDao;

    public UserCompanyJobsServiceImpl(UserCompanyJobsDao userCompanyJobsDao) {
        this.userCompanyJobsDao = userCompanyJobsDao;
    }

    @Override
    public void save(String userId, String companyId, UserCompanyJobs sourceInfo) {
        if (sourceInfo == null) {
            sourceInfo = new UserCompanyJobs();
            sourceInfo.setUserId(userId);
            sourceInfo.setCompanyId(companyId);
        }
        this.userCompanyJobsDao.save(sourceInfo);
    }

    @Override
    public UserCompanyJobs get(String userId) {
        Optional<UserCompanyJobs> userCompanyJobs = this.userCompanyJobsDao.findById(userId);
        if (!userCompanyJobs.isPresent()) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        return userCompanyJobs.get();
    }
}
