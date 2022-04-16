package com.hrm.system.client;

import com.hrm.api.company.DepartmentControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 12:26
 * @Description: 声明调用部门服务接口
 */
@FeignClient("hrm-company")
public interface DepartmentFeignClient extends DepartmentControllerApi {
}
