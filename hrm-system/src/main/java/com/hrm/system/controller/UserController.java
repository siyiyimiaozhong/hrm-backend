package com.hrm.system.controller;

import com.hrm.common.controller.BaseController;
import com.hrm.common.entity.PageResult;
import com.hrm.common.entity.Result;
import com.hrm.domain.system.User;
import com.hrm.domain.system.dto.UserDto;
import com.hrm.domain.system.dto.UserRoleDto;
import com.hrm.domain.system.vo.UserVo;
import com.hrm.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 19:19
 * @Description: 用户前端控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result<Object> save(@RequestBody User user) {
        user.setCompanyId(this.companyId);
        user.setCompanyName(this.companyName);
        this.userService.checkAndInsert(user);
        return Result.success();
    }

    /**
     * 分页查询用户信息
     *
     * @return
     */
    @PostMapping("/page")
    public Result<PageResult<User>> page(@RequestBody UserDto userDto) {
        userDto.setCompanyId(this.companyId);
        PageResult<User> userPage = this.userService.findAll(userDto);
        return Result.success(userPage);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable("id") String id) {
        UserVo user = this.userService.findUserVoById(id);
        return Result.success(user);
    }

    /**
     * 修改部门信息
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Result<Object> update(@PathVariable("id") String id, @RequestBody User user) {
        this.userService.checkAndUpdate(id, user);
        return Result.success();
    }

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    @RequiresPermissions(value = "API-USER-DELETE")
    @DeleteMapping(value = "/{ids}", name = "API-USER-DELETE")
    public Result<Object> delete(@PathVariable("ids") String... ids) {
        this.userService.delete(ids);
        return Result.success();
    }

    /**
     * 添加对应角色
     *
     * @param userRoleDto
     * @return
     */
    @PutMapping("/assignRoles")
    public Result<Object> assignRoles(@RequestBody UserRoleDto userRoleDto) {
        this.userService.assignRoles(userRoleDto);
        return Result.success();
    }
}
