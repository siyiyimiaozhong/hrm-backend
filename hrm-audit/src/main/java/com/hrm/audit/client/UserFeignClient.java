package com.hrm.audit.client;

import com.hrm.api.system.UserControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:43
 * @Description: 用户信息远程调用接口
 */
@FeignClient("hrm-system")
public interface UserFeignClient extends UserControllerApi {
}
