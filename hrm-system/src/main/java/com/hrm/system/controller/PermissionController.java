package com.hrm.system.controller;

import com.hrm.common.entity.Result;
import com.hrm.domain.system.Permission;
import com.hrm.system.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 07:28
 * @Description: 权限控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/perm")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 保存权限数据
     *
     * @param map
     * @return
     */
    @PostMapping
    public Result<Object> save(@RequestBody Map<String, Object> map) throws Exception {
        this.permissionService.checkAndInsert(map);
        return Result.success();
    }

    /**
     * 修改权限数据
     *
     * @param id
     * @param map
     * @return
     */
    @PutMapping("/{id}")
    public Result<Object> update(@PathVariable("id") String id, @RequestBody Map<String, Object> map) throws Exception {
        this.permissionService.checkAndUpdate(id, map);
        return Result.success();
    }

    /**
     * 获取所有权限
     *
     * @param map
     * @return
     */
    @GetMapping
    public Result<List<Permission>> findAll(@RequestParam Map<String, Object> map) {
        List<Permission> list = this.permissionService.findAll(map);
        return Result.success(list);
    }

    /**
     * 通过id获取权限数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Map<String, Object>> get(@PathVariable("id") String id) {
        Map<String, Object> map = this.permissionService.findById(id);
        return Result.success(map);
    }

    /**
     * 根据id删除权限
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result<Object> delete(@PathVariable("ids") String... ids) {
        this.permissionService.delete(ids);
        return Result.success();
    }
}
