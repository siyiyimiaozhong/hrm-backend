package com.hrm.employee.service.impl;

import com.hrm.core.constant.ResultCode;
import com.hrm.core.exception.BusinessException;
import com.hrm.employee.dao.ResignationDao;
import com.hrm.employee.service.ResignationService;
import com.hrm.model.employee.entity.Resignation;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 14:56
 * @Description: 离职申请业务层实现类
 */
@Service
public class ResignationServiceImpl implements ResignationService {

    private final ResignationDao resignationDao;

    public ResignationServiceImpl(ResignationDao resignationDao) {
        this.resignationDao = resignationDao;
    }

    @Override
    public void save(String userId, Resignation resignation) {
        resignation.setUserId(userId);
        resignation.setCreateTime(new Date());
        this.resignationDao.save(resignation);
    }

    @Override
    public Resignation get(String userId) {
        Resignation resignation = this.resignationDao.findByUserId(userId);
        if (null == resignation) {
            return new Resignation();
        }
        return resignation;
    }
}
