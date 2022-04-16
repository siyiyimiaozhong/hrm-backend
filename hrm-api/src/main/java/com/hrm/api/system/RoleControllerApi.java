package com.hrm.api.system;

import com.hrm.core.entity.PageResult;
import com.hrm.core.entity.Result;
import com.hrm.model.system.Role;
import com.hrm.model.system.dto.RoleDto;
import com.hrm.model.system.vo.RoleVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:49
 * @Description: 角色Api
 */
public interface RoleControllerApi {
    /**
     * 分配权限
     *
     * @param roleDto
     * @return
     */
    Result<Object> assignPerm(@RequestBody RoleDto roleDto);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    Result<Object> add(@RequestBody Role role);

    /**
     * 更新角色信息
     *
     * @param id
     * @param role
     * @return
     */
    Result<Object> update(@PathVariable("id") String id, @RequestBody Role role);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    Result<Object> delete(@PathVariable("ids") String... ids);

    /**
     * 根据id获取角色信息
     *
     * @param id
     * @return
     */
    Result<RoleVo> get(@PathVariable("id") String id);

    /**
     * 分页查询角色
     *
     * @param page
     * @param size
     * @return
     */
    Result<PageResult<Role>> page(int page, int size);

    /**
     * 查询全部角色
     *
     * @return
     */
    Result<List<Role>> list();
}
