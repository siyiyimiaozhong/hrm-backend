package com.hrm.attendance.controller;

import com.hrm.api.attendance.AttendanceControllerApi;
import com.hrm.attendance.service.ArchiveService;
import com.hrm.attendance.service.AttendanceService;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.ArchiveMonthly;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.attendance.entity.Attendance;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:15
 * @Description: 考勤控制器
 */
@RestController
public class AttendanceController extends BaseController implements AttendanceControllerApi {

    private final AttendanceService attendanceService;
    private final ArchiveService archiveService;

    public AttendanceController(AttendanceService attendanceService, ArchiveService archiveService) {
        this.attendanceService = attendanceService;
        this.archiveService = archiveService;
    }

    /**
     * 上传考勤数据
     *
     * @param file
     * @return
     */
    @Override
    public Result<Object> importExcel(@RequestParam(name = "file") MultipartFile file) {
        this.attendanceService.importAttendanceExcel(file, companyId);
        return Result.success();
    }

    /**
     * 查询考勤数据列表
     *
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    public Result<Map<String, Object>> page(int page, int pagesize) {
        Map<String, Object> map = this.attendanceService.getAtteDate(companyId, page, pagesize);
        return Result.success(map);
    }

    /**
     * 编辑用户的考勤记录
     *
     * @param attendance
     * @return
     */
    @Override
    public Result<Object> editAtte(Attendance attendance) {
        this.attendanceService.updateAtte(attendance);
        return Result.success();
    }

    /**
     * 获取月报表归档数据
     *
     * @param atteDate
     * @return
     */
    @Override
    public Result<List<ArchiveMonthlyInfo>> reports(String atteDate) {
        List<ArchiveMonthlyInfo> reports = this.attendanceService.getReports(atteDate, companyId);
        return Result.success(reports);
    }

    /**
     * 数据归档
     *
     * @param archiveDate
     * @return
     */
    @Override
    public Result<Object> item(String archiveDate) {
        archiveService.saveArchive(archiveDate, companyId);
        return Result.success();
    }

    /**
     * 新建报表
     *
     * @param yearMonth
     * @return
     */
    @Override
    public Result<Object> newReports(String yearMonth) {
        this.attendanceService.newReports(yearMonth, companyId);
        return Result.success();
    }

    /**
     * 归档历史列表
     *
     * @param year
     * @return
     */
    @Override
    public Result<List<ArchiveMonthly>> findReportsByYear(String year) {
        List<ArchiveMonthly> list = this.archiveService.findReportsByYear(year, companyId);
        return Result.success(list);
    }

    /**
     * 查询归档详情
     *
     * @param id 归档历史主表的id
     * @return
     */
    @Override
    public Result<List<ArchiveMonthlyInfo>> findInfosById(String id) {
        List<ArchiveMonthlyInfo> list = this.archiveService.findMonthlyInfoByAmid(id);
        return Result.success(list);
    }

    /**
     * 根据用户id和月份 查询已归档的考勤明细
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    @Override
    public Result<ArchiveMonthlyInfo> historyData(String userId, String yearMonth) {
        ArchiveMonthlyInfo info = this.archiveService.findUserArchiveDetail(userId, yearMonth);
        return Result.success(info);
    }
}
