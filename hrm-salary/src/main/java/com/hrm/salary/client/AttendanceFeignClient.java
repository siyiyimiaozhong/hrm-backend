package com.hrm.salary.client;

import com.hrm.api.attendance.AttendanceControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:30
 * @Description: 考勤API远程调用接口
 */
@FeignClient("hrm-attendance")
public interface AttendanceFeignClient extends AttendanceControllerApi {
}
