package com.hrm.api.system;

import com.hrm.core.pojo.Result;
import com.hrm.model.system.entity.Permission;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:46
 * @Description: 权限Api
 */
public interface PermissionControllerApi {
    /**
     * 保存权限数据
     *
     * @param map
     * @return
     * @throws Exception
     */
    Result<Object> save(@RequestBody Map<String, Object> map) throws Exception;

    /**
     * 修改权限数据
     *
     * @param id
     * @param map
     * @return
     * @throws Exception
     */
    Result<Object> update(@PathVariable("id") String id, @RequestBody Map<String, Object> map) throws Exception;

    /**
     * 获取所有权限
     *
     * @param map
     * @return
     */
    Result<List<Permission>> findAll(@RequestParam Map<String, Object> map);

    /**
     * 通过id获取权限数据
     *
     * @param id
     * @return
     */
    Result<Map<String, Object>> get(@PathVariable("id") String id);

    /**
     * 根据id删除权限
     *
     * @param ids
     * @return
     */
    Result<Object> delete(@PathVariable("ids") String... ids);
}
