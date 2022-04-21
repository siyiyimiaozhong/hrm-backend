package com.hrm.attendance.controller;

import com.hrm.api.attendance.AttendanceControllerApi;
import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.ArchiveMonthly;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.attendance.entity.Attendance;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:15
 * @Description: 考勤控制器
 */
public class AttendanceController implements AttendanceControllerApi {
    @Override
    public Result<Object> importExcel(MultipartFile file) {
        return null;
    }

    @Override
    public Result<Map<String, Object>> importExcel(int page, int pagesize) {
        return null;
    }

    @Override
    public Result<Object> editAtte(Attendance attendance) {
        return null;
    }

    @Override
    public Result<List<ArchiveMonthlyInfo>> reports(String atteDate) {
        return null;
    }

    @Override
    public Result<Object> item(String archiveDate) {
        return null;
    }

    @Override
    public Result<Object> newReports(String yearMonth) {
        return null;
    }

    @Override
    public Result<List<ArchiveMonthly>> findReportsByYear(String year) {
        return null;
    }

    @Override
    public Result<List<ArchiveMonthlyInfo>> findInfosById(String id) {
        return null;
    }

    @Override
    public Result<ArchiveMonthlyInfo> historyData(String userId, String yearMonth) {
        return null;
    }
}
