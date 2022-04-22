package com.hrm.attendance.service;

import com.hrm.model.attendance.entity.ArchiveMonthly;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 09:04
 * @Description: 归档业务层接口
 */
public interface ArchiveService {
    /**
     * 数据归档
     *
     * @param archiveDate
     * @param companyId
     */
    void saveArchive(String archiveDate, String companyId);

    /**
     * 归档历史列表
     *
     * @param year
     * @param companyId
     * @return
     */
    List<ArchiveMonthly> findReportsByYear(String year, String companyId);

    /**
     * 查询归档详情
     *
     * @param id
     * @return
     */
    List<ArchiveMonthlyInfo> findMonthlyInfoByAmid(String id);

    /**
     * 根据用户id和月份 查询已归档的考勤明细
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    ArchiveMonthlyInfo findUserArchiveDetail(String userId, String yearMonth);
}
