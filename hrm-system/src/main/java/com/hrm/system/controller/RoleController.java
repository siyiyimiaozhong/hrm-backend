package com.hrm.system.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.PageResult;
import com.hrm.common.entity.Result;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.dto.RoleDto;
import com.hrm.domain.system.vo.RoleVo;
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
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
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
    @PutMapping("/assignPerm")
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
    @PostMapping
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{ids}")
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
    @GetMapping("/{id}")
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
    @GetMapping("/page")
    public Result<PageResult<Role>> page(int page, int size) {
        PageResult<Role> pageResult = this.roleService.page(this.companyId, page, size);
        return Result.success(pageResult);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @GetMapping("/list")
    public Result<List<Role>> list() {
        List<Role> roles = this.roleService.findAllByCompanyId(this.companyId);
        return Result.success(roles);
    }
}
