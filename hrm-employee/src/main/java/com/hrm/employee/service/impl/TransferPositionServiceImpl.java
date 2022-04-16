package com.hrm.employee.service.impl;

import com.hrm.employee.dao.TransferPositionDao;
import com.hrm.employee.service.TransferPositionService;
import com.hrm.model.employee.TransferPosition;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 14:57
 * @Description: 员工调岗申请业务层实现类
 */
@Service
public class TransferPositionServiceImpl implements TransferPositionService {

    private final TransferPositionDao transferPositionDao;

    public TransferPositionServiceImpl(TransferPositionDao transferPositionDao) {
        this.transferPositionDao = transferPositionDao;
    }

    @Override
    public void save(String userId, TransferPosition transferPosition) {
        transferPosition.setUserId(userId);
        this.transferPositionDao.save(transferPosition);
    }
}
