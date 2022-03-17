package com.hrm.system.service;

import com.hrm.common.entity.PageResult;
import com.hrm.domain.system.User;
import com.hrm.domain.system.dto.UserDto;
import com.hrm.domain.system.dto.UserRoleDto;
import com.hrm.domain.system.vo.ProfileVo;
import com.hrm.domain.system.vo.UserVo;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:21
 * @Description: 用户业务层接口
 */
public interface UserService {
    /**
     * 校验用户信息并保存
     *
     * @param user
     */
    void checkAndInsert(User user);

    /**
     * 校验并更新用户信息
     *
     * @param user
     */
    void checkAndUpdate(String id, User user);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(String id);

    /**
     * 通过手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);

    /**
     * 根据id删除用户信息
     *
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 根据条件分页查询
     *
     * @param userDto
     * @return
     */
    PageResult<User> findAll(UserDto userDto);

    /**
     * 添加角色信息
     *
     * @param userRoleDto
     */
    void assignRoles(UserRoleDto userRoleDto);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    String login(String mobile, String password);

    /**
     * 获取用户信息
     *
     * @return
     */
    ProfileVo profile();
}
