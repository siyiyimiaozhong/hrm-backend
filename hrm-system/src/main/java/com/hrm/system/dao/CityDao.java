package com.hrm.system.dao;

import com.hrm.model.system.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 12:45
 * @Description: 城市信息持久层接口
 */
public interface CityDao extends JpaRepository<City,String>, JpaSpecificationExecutor<City> {
}