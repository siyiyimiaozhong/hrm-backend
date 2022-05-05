package com.hrm.api.attendance;

import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.ArchiveMonthly;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.attendance.entity.Attendance;
import com.hrm.model.attendance.vo.AttendanceDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:15
 * @Description: 考勤控制器Api
 */
@RequestMapping("/attendances")
public interface AttendanceControllerApi {

    /**
     * 上传考勤数据
     * @param file
     * @return
     */
    @PostMapping("/import")
    Result<Object> importExcel(@RequestParam(name="file") MultipartFile file);

    /**
     * 查询考勤数据列表
     * @param attendanceDto
     * @return
     * @throws Exception
     */
    @PostMapping
    Result<Map<String, Object>> page(@RequestBody AttendanceDto attendanceDto);

    /**
     * 编辑用户的考勤记录
     * @param attendance
     * @return
     * @throws Exception
     */
    @PutMapping("/{id}")
    Result<Object> editAtte(@RequestBody Attendance attendance);

    /**
     *
     *
     */
    /**
     * 获取月报表归档数据
     *  /attendances/archive/item?archiveDate=202202
     * @param atteDate
     * @return
     * @throws Exception
     */
    @GetMapping("/reports")
    Result<List<ArchiveMonthlyInfo>> reports(@RequestParam("atteDate") String atteDate);

    /**
     * 数据归档
     *  /attendances/archive/item?archiveDate=202202
     * @param archiveDate
     * @return
     * @throws Exception
     */
    @GetMapping("/archive/item")
    Result<Object> item(String archiveDate);

    /**
     * 新建报表
     *  /attendances/newReports?atteDate=202202&yearMonth=202202
     * @param yearMonth
     * @return
     * @throws Exception
     */
    @GetMapping("/newReports")
    Result<Object> newReports(String yearMonth);

    /**
     * 归档历史列表
     *  /attendances/reports/year?departmentId=1063676045212913664&year=2022
     * @param year
     * @return
     * @throws Exception
     */
    @GetMapping("/reports/year")
    Result<List<ArchiveMonthly>> findReportsByYear(String year);

    /**
     * 查询归档详情
     * @param id 归档历史主表的id
     * @return
     * @throws Exception
     */
    @GetMapping("/reports/{id}")
    Result<List<ArchiveMonthlyInfo>> findInfosById(@PathVariable String id);

    /**
     * 根据用户id和月份
     *  查询已归档的考勤明细
     *  /archive/{userId}/{yearMonth}
     * @param userId
     * @param yearMonth
     * @return
     */
    @GetMapping ("/archive/{userId}/{yearMonth}")
    Result<ArchiveMonthlyInfo> historyData(@PathVariable("userId") String userId,@PathVariable("yearMonth") String yearMonth);
}
