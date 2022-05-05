package com.hrm.audit.service;

import com.hrm.model.system.vo.UserVo;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:33
 * @Description: 远程调用业务层接口
 */
public interface FeignClientService {
    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    UserVo getUserInfoByUserId(String userId);
}
