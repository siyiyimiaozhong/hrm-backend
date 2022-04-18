package com.hrm.system.service;

import com.hrm.model.system.City;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 12:44
 * @Description: 城市信息业务层接口
 */
public interface CityService {
    /**
     * 保存城市信息
     * @param city
     */
    void saveCity(City city);

    /**
     * 根据id修改城市信息
     * @param id
     * @param city
     */
    void update(String id, City city);

    /**
     * 根据id删除城市信息
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id获取城市信息
     * @param id
     * @return
     */
    City get(String id);

    /**
     * 获取所有的额城市列表
     * @return
     */
    List<City> findAll();
}
