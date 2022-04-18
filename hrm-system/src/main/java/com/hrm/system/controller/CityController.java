package com.hrm.system.controller;

import com.hrm.api.system.CityControllerApi;
import com.hrm.core.entity.Result;
import com.hrm.model.system.City;
import com.hrm.system.service.CityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 12:42
 * @Description: 城市信息控制器
 */
@CrossOrigin
@RestController
public class CityController implements CityControllerApi {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public Result<Object> save(@RequestBody City city) {
        this.cityService.saveCity(city);
        return Result.success();
    }

    @Override
    public Result<Object> update(@PathVariable("id") String id, @RequestBody City city) {
        this.cityService.update(id, city);
        return Result.success();
    }

    @Override
    public Result<Object> delete(@PathVariable("id") String id) {
        this.cityService.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<City> findById(@PathVariable("id") String id) {
        City city = this.cityService.get(id);
        return Result.success(city);
    }

    @Override
    public Result<List<City>> findAll() {
        List<City> cities = this.cityService.findAll();
        return Result.success(cities);
    }
}
