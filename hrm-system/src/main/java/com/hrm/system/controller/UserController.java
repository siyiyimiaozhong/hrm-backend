package com.hrm.system.controller;

import com.hrm.api.system.UserControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.entity.PageResult;
import com.hrm.core.entity.Result;
import com.hrm.model.company.Department;
import com.hrm.model.system.User;
import com.hrm.model.system.dto.UserDto;
import com.hrm.model.system.dto.UserRoleDto;
import com.hrm.model.system.vo.UserSimpleVo;
import com.hrm.model.system.vo.UserVo;
import com.hrm.system.client.DepartmentFeignClient;
import com.hrm.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 19:19
 * @Description: 用户前端控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController implements UserControllerApi {
    private final UserService userService;
    private final DepartmentFeignClient departmentFeignClient;

    public UserController(UserService userService, DepartmentFeignClient departmentFeignClient) {
        this.userService = userService;
        this.departmentFeignClient = departmentFeignClient;
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Result<Object> assignRoles(@RequestBody UserRoleDto userRoleDto) {
        this.userService.assignRoles(userRoleDto);
        return Result.success();
    }

    @GetMapping("/simple")
    @Override
    public Result<List<UserSimpleVo>> simple() {
        List<UserSimpleVo> list = this.userService.getSimpleInfo(super.companyId);
        return Result.success(list);
    }

    @GetMapping("/template")
    @Override
    public void template(HttpServletResponse response) {
        this.userService.getUserTemplateExcel(response);
    }

    @PostMapping("/import")
    @Override
    public Result<Object> importUser(@RequestParam("file") MultipartFile file) {
        this.userService.importUserByExcel(super.companyId, super.companyName, file);
        return Result.success();
    }

    @GetMapping("/test/{id}")
    public Result<Department> testFeign(@PathVariable("id") String id) {
        return this.departmentFeignClient.get(id);
    }
}
