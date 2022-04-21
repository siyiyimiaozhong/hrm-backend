package com.hrm.employee.service.impl;

import com.hrm.core.constant.ResultCode;
import com.hrm.core.exception.BusinessException;
import com.hrm.employee.dao.PositiveDao;
import com.hrm.employee.service.PositiveService;
import com.hrm.model.employee.entity.Positive;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 14:56
 * @Description: 转正申请业务层实现类
 */
@Service
public class PositiveServiceImpl implements PositiveService {

    private final PositiveDao positiveDao;

    public PositiveServiceImpl(PositiveDao positiveDao) {
        this.positiveDao = positiveDao;
    }

    @Override
    public void save(String userId, Positive positive) {
        positive.setUserId(userId);
        this.positiveDao.save(positive);
    }

    @Override
    public Positive get(String userId) {
        Positive positive = this.positiveDao.findByUserId(userId);
        if (null == positive) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        return positive;
    }
}
