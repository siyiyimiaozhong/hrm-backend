package com.hrm.audit.service.impl;

import com.alibaba.fastjson.JSON;
import com.hrm.audit.client.UserFeignClient;
import com.hrm.audit.service.FeignClientService;
import com.hrm.core.pojo.Result;
import com.hrm.model.system.vo.UserVo;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:33
 * @Description: 远程调用业务层实现类
 */
@Service
public class FeignClientServiceImpl implements FeignClientService {

    private final UserFeignClient userFeignClient;

    public FeignClientServiceImpl(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserVo getUserInfoByUserId(String userId) {
        if (userId == null) throw new RuntimeException("获取当前用户为空！");
        Result<UserVo> result = this.userFeignClient.get(userId);
        if (!result.isSuccess()) throw new RuntimeException("用户ID不存在，请联系管理员：" + userId);
        return JSON.parseObject(JSON.toJSONString(result.getData()), UserVo.class);
    }
}
