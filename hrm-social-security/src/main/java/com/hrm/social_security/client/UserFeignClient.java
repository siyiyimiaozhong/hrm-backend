package com.hrm.social_security.client;

import com.hrm.api.system.UserControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 19:03
 * @Description: 远程调用用户接口
 */
@FeignClient("hrm-system")
public interface UserFeignClient extends UserControllerApi {
}
