package com.hrm.api.system;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.system.entity.User;
import com.hrm.model.system.dto.UserDto;
import com.hrm.model.system.dto.UserRoleDto;
import com.hrm.model.system.vo.UserSimpleVo;
import com.hrm.model.system.vo.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:58
 * @Description: 用户Api
 */
@RequestMapping("/sys/user")
public interface UserControllerApi {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping
    Result<Object> save(@RequestBody User user);

    /**
     * 分页查询用户信息
     *
     * @param userDto
     * @return
     */
    @PostMapping("/page")
    Result<PageResult<User>> page(@RequestBody UserDto userDto);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<UserVo> get(@PathVariable("id") String id);

    /**
     * 修改部门信息
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    Result<Object> update(@PathVariable("id") String id, @RequestBody User user);

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    @RequiresPermissions(value = "API-USER-DELETE")
    @DeleteMapping(value = "/{ids}", name = "API-USER-DELETE")
    Result<Object> delete(@PathVariable("ids") String... ids);

    /**
     * 添加对应角色
     *
     * @param userRoleDto
     * @return
     */
    @PutMapping("/assignRoles")
    Result<Object> assignRoles(@RequestBody UserRoleDto userRoleDto);

    /**
     * 获取用户用户简要信息列表
     *
     * @return
     */
    @GetMapping("/simple")
    Result<List<UserSimpleVo>> simple();

    /**
     * 获取用户导入模板excel
     *
     * @param response
     * @return
     */
    @GetMapping("/template")
    void template(HttpServletResponse response);

    /**
     * 导入excel，添加用户
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    Result<Object> importUser(@RequestParam("file") MultipartFile file);

    /**
     * 上传头像，并转化为DataURL返回
     *
     * @param id
     * @param file
     * @return
     */
    @PostMapping("/upload/{id}")
    Result<String> uploadImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file);
}
