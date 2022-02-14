package com.hrm.system.service;

import com.hrm.domain.system.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:46
 * @Description: 权限业务层接口
 */
public interface PermissionService {
    /**
     * 校验并添加权限数据
     *
     * @param map
     */
    void checkAndInsert(Map<String, Object> map) throws Exception;

    /**
     * 校验并修改权限数据
     *
     * @param id
     * @param map
     */
    void checkAndUpdate(String id, Map<String, Object> map) throws Exception;

    /**
     * 获取所有权限数据
     *
     * @param map
     * @return
     */
    List<Permission> findAll(Map<String, Object> map);

    /**
     * 根据id获取权限数据
     *
     * @param id
     * @return
     */
    Map<String, Object> findById(String id);

    /**
     * 根据id删除权限
     *
     * @param ids
     */
    void delete(String[] ids);
}
