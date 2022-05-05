package com.hrm.audit.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:44
 * @Description: 城市信息远程调用接口
 */
@FeignClient("hrm-system")
public interface CityFeignClient {
}
