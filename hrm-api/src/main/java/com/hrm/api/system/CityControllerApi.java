package com.hrm.api.system;

import com.hrm.core.entity.Result;
import com.hrm.model.system.City;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 12:34
 * @Description: 城市信息Api接口
 */
@RequestMapping(value = "/sys/city")
public interface CityControllerApi {
    /**
     * 保存
     *
     * @param city
     * @return
     */
    @PostMapping
    Result<Object> save(@RequestBody City city);

    /**
     * 根据id更新
     *
     * @param id
     * @param city
     * @return
     */
    @PutMapping("/{id}")
    Result<Object> update(@PathVariable(value = "id") String id, @RequestBody City city);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    Result<Object> delete(@PathVariable(value = "id") String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    Result<City> findById(@PathVariable(value = "id") String id);

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping
    Result<List<City>> findAll();
}
