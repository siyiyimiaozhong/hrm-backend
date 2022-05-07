package com.hrm.api.system;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.system.entity.Role;
import com.hrm.model.system.dto.RoleDto;
import com.hrm.model.system.vo.RoleVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:49
 * @Description: 角色Api
 */
@RequestMapping("/sys/role")
public interface RoleControllerApi {
    /**
     * 分配权限
     *
     * @param roleDto
     * @return
     */
    @PutMapping("/assignPerm")
    Result<Object> assignPerm(@RequestBody RoleDto roleDto);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping
    Result<Object> add(@RequestBody Role role);

    /**
     * 更新角色信息
     *
     * @param id
     * @param role
     * @return
     */
    @PutMapping("/{id}")
    Result<Object> update(@PathVariable("id") String id, @RequestBody Role role);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    Result<Object> delete(@PathVariable("ids") String... ids);

    /**
     * 根据id获取角色信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<RoleVo> get(@PathVariable("id") String id);

    /**
     * 分页查询角色
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/page")
    Result<PageResult<Role>> page(int page, int size);

    /**
     * 查询全部角色
     *
     * @return
     */
    @GetMapping("/list")
    Result<List<Role>> list();
}
