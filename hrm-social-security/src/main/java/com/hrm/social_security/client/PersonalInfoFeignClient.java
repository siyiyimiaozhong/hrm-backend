package com.hrm.social_security.client;

import com.hrm.api.employee.PersonalInfoControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-18 07:29
 * @Description: 员工个人信息远程调用接口
 */
@FeignClient("hrm-employee")
public interface PersonalInfoFeignClient extends PersonalInfoControllerApi {
}
