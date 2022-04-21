package com.hrm.social_security.dao;

import com.hrm.model.social_security.entity.PaymentItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:49
 * @Description: 社保缴费项目持久层接口
 */
public interface PaymentItemDao extends JpaRepository<PaymentItem, String>, JpaSpecificationExecutor<PaymentItem> {
}
