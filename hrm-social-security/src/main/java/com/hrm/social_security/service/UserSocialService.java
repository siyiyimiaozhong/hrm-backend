package com.hrm.social_security.service;

import com.hrm.core.pojo.PageResult;
import com.hrm.model.social_security.entity.UserSocialSecurity;

import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:54
 * @Description: 用户社保信息业务层接口
 */
public interface UserSocialService {
    /**
     * 分页查询员工社保信息列表
     *
     * @param page
     * @param pageSize
     * @param companyId
     * @return
     */
    PageResult<Map<String, Object>> list(Integer page, Integer pageSize, String companyId);

    /**
     * 根据用户id查询员工的社保数据
     *
     * @param id
     * @return
     */
    Map<String, Object> get(String id);

    /**
     * 保存或更新用户社保
     *
     * @param uss
     */
    void saveUserSocialSecurity(UserSocialSecurity uss);
}
