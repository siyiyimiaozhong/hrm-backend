package com.hrm.api.system;

import com.hrm.core.entity.PageResult;
import com.hrm.core.entity.Result;
import com.hrm.model.system.User;
import com.hrm.model.system.dto.UserDto;
import com.hrm.model.system.dto.UserRoleDto;
import com.hrm.model.system.vo.UserSimpleVo;
import com.hrm.model.system.vo.UserVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:58
 * @Description: 用户Api
 */
public interface UserControllerApi {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Result<Object> save(@RequestBody User user);

    /**
     * 分页查询用户信息
     *
     * @param userDto
     * @return
     */
    Result<PageResult<User>> page(@RequestBody UserDto userDto);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    Result<UserVo> get(@PathVariable("id") String id);

    /**
     * 修改部门信息
     *
     * @param id
     * @param user
     * @return
     */
    Result<Object> update(@PathVariable("id") String id, @RequestBody User user);

    /**
     * 根据id删除用户
     *
     * @param ids
     * @return
     */
    Result<Object> delete(@PathVariable("ids") String... ids);

    /**
     * 添加对应角色
     *
     * @param userRoleDto
     * @return
     */
    Result<Object> assignRoles(@RequestBody UserRoleDto userRoleDto);

    /**
     * 获取用户用户简要信息列表
     *
     * @return
     */
    Result<List<UserSimpleVo>> simple();

    /**
     * 获取用户导入模板excel
     *
     * @param response
     * @return
     */
    public void template(HttpServletResponse response);

    /**
     * 导入excel，添加用户
     *
     * @param file
     * @return
     */
    Result<Object> importUser(@RequestParam("file") MultipartFile file);

    /**
     * 上传头像，并转化为DataURL返回
     * @param id
     * @param file
     * @return
     */
    Result<String> uploadImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file);
}
