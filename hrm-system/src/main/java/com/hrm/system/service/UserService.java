package com.hrm.system.service;

import com.hrm.common.entity.PageResult;
import com.hrm.domain.system.User;
import com.hrm.domain.system.dto.UserDto;
import org.springframework.data.domain.Page;

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
    User findById(String id);

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
}
