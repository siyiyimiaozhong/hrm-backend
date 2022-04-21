package com.hrm.social_security.service;

import com.hrm.model.social_security.entity.CityPaymentItem;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:53
 * @Description: 社保缴费项目业务接口
 */
public interface PaymentItemService {
    /**
     * 根据城市id查询参保城市的参保项目
     *
     * @param id
     * @return
     */
    List<CityPaymentItem> findAllByCityId(String id);
}
