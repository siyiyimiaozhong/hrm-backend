package com.hrm.attendance.service;

import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.attendance.entity.Attendance;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:06
 * @Description: 考勤业务层接口
 */
public interface AttendanceService {
    /**
     * 上传考勤数据
     * @param file
     * @param companyId
     */
    void importAttendanceExcel(MultipartFile file, String companyId);

    /**
     * 查询考勤数据列表
     * @param companyId
     * @param page
     * @param pagesize
     * @return
     */
    Map<String, Object> getAtteDate(String companyId, int page, int pagesize);

    /**
     * 编辑用户的考勤记录
     * @param attendance
     */
    void updateAtte(Attendance attendance);

    /**
     * 获取月报表归档数据
     * @param atteDate
     * @param companyId
     * @return
     */
    List<ArchiveMonthlyInfo> getReports(String atteDate, String companyId);

    /**
     * 新建报表
     * @param yearMonth
     * @param companyId
     */
    void newReports(String yearMonth, String companyId);
}
