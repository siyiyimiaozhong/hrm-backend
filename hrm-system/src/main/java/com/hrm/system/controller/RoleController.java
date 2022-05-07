package com.hrm.system.controller;

import com.hrm.api.system.RoleControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.system.entity.Role;
import com.hrm.model.system.dto.RoleDto;
import com.hrm.model.system.vo.RoleVo;
import com.hrm.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:50
 * @Description: 角色控制层
 */
@CrossOrigin
@RestController
public class RoleController extends BaseController implements RoleControllerApi {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 分配权限
     *
     * @param roleDto
     * @return
     */
    @Override
    public Result<Object> assignPerm(@RequestBody RoleDto roleDto) {
        this.roleService.assignPerms(roleDto);
        return Result.success();
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public Result<Object> add(@RequestBody Role role) {
        role.setCompanyId(this.companyId);
        this.roleService.checkAndInsert(role);
        return Result.success();
    }

    /**
     * 更新角色信息
     *
     * @param id
     * @param role
     * @return
     */
    @Override
    public Result<Object> update(@PathVariable("id") String id, @RequestBody Role role) {
        this.roleService.checkAndUpdate(id, role);
        return Result.success();
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @Override
    public Result<Object> delete(@PathVariable("ids") String... ids) {
        this.roleService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id获取角色信息
     *
     * @param id
     * @return
     */
    @Override
    public Result<RoleVo> get(@PathVariable("id") String id) {
        RoleVo role = this.roleService.findRoleVoById(id);
        return Result.success(role);
    }

    /**
     * 分页查询角色
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Result<PageResult<Role>> page(int page, int size) {
        PageResult<Role> pageResult = this.roleService.page(this.companyId, page, size);
        return Result.success(pageResult);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @Override
    public Result<List<Role>> list() {
        List<Role> roles = this.roleService.findAllByCompanyId(this.companyId);
        return Result.success(roles);
    }
}
