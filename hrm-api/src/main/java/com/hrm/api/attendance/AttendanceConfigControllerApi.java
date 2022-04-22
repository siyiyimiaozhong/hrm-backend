package com.hrm.api.attendance;

import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.AttendanceConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:13
 * @Description: 考勤配置Api
 */
@RequestMapping("/cfg")
public interface AttendanceConfigControllerApi {

    /**
     * 获取考勤设置
     *
     * @param departmentId
     * @return
     */
    @PostMapping("/atte/item")
    Result<AttendanceConfig> attendanceConfig(String departmentId);

    /**
     * 保存考勤设置
     *
     * @param attendanceConfig
     * @return
     */
    @PutMapping("/atte")
    Result<Object> updateAtteConfig(@RequestBody AttendanceConfig attendanceConfig);
}
