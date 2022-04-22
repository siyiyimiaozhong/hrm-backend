package com.hrm.attendance.controller;

import com.hrm.api.attendance.AttendanceConfigControllerApi;
import com.hrm.attendance.service.AttendanceConfigService;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.AttendanceConfig;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:13
 * @Description: 考勤配置控制器
 */
@RestController
public class AttendanceConfigController extends BaseController implements AttendanceConfigControllerApi {

    private final AttendanceConfigService attendanceConfigService;

    public AttendanceConfigController(AttendanceConfigService attendanceConfigService) {
        this.attendanceConfigService = attendanceConfigService;
    }

    @Override
    public Result<AttendanceConfig> attendanceConfig(String departmentId) {
        AttendanceConfig ac = this.attendanceConfigService.getAtteConfig(super.companyId, departmentId);
        return Result.success(ac);
    }

    @Override
    public Result<Object> updateAtteConfig(AttendanceConfig attendanceConfig) {
        this.attendanceConfigService.saveAndUpdateAtteConfig(companyId, attendanceConfig);
        return Result.success();
    }
}
