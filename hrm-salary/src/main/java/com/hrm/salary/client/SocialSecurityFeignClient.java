package com.hrm.salary.client;

import com.hrm.api.social_security.SocialSecurityControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:31
 * @Description: 社保API远程调用接口
 */
@FeignClient("hrm-social-security")
public interface SocialSecurityFeignClient extends SocialSecurityControllerApi {
}
