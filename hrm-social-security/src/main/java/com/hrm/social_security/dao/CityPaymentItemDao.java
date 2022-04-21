package com.hrm.social_security.dao;

import com.hrm.model.social_security.entity.CityPaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:47
 * @Description: 社保-城市与缴费项目持久层接口
 */
public interface CityPaymentItemDao extends JpaRepository<CityPaymentItem, String>, JpaSpecificationExecutor<CityPaymentItem> {
    List<CityPaymentItem> findAllByCityId(String id);
}