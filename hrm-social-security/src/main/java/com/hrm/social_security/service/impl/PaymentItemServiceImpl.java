package com.hrm.social_security.service.impl;

import com.hrm.model.social_security.CityPaymentItem;
import com.hrm.social_security.dao.CityPaymentItemDao;
import com.hrm.social_security.service.PaymentItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:57
 * @Description: 社保缴费项目业务层实现类
 */
@Service
public class PaymentItemServiceImpl implements PaymentItemService {

    private final CityPaymentItemDao cityPaymentItemDao;

    public PaymentItemServiceImpl(CityPaymentItemDao cityPaymentItemDao) {
        this.cityPaymentItemDao = cityPaymentItemDao;
    }

    @Override
    public List<CityPaymentItem> findAllByCityId(String id) {
        return this.cityPaymentItemDao.findAllByCityId(id);
    }
}
