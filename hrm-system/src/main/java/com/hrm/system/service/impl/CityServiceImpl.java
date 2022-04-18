package com.hrm.system.service.impl;

import com.hrm.common.utils.IdWorker;
import com.hrm.model.system.City;
import com.hrm.system.dao.CityDao;
import com.hrm.system.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 12:45
 * @Description: 城市信息业务层实现类
 */
@Service
public class CityServiceImpl implements CityService {

    private final CityDao cityDao;
    private final IdWorker idWorker;

    public CityServiceImpl(CityDao cityDao, IdWorker idWorker) {
        this.cityDao = cityDao;
        this.idWorker = idWorker;
    }

    @Override
    public void saveCity(City city) {
        String id = idWorker.nextId() + "";
        city.setId(id);
        this.cityDao.save(city);
    }

    @Override
    public void update(String id, City city) {
        city.setId(id);
        this.cityDao.save(city);
    }

    @Override
    public void deleteById(String id) {
        this.cityDao.deleteById(id);
    }

    @Override
    public City get(String id) {
        return this.cityDao.findById(id).orElse(null);
    }

    @Override
    public List<City> findAll() {
        return this.cityDao.findAll();
    }
}
