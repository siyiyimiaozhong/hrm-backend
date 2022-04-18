package com.hrm.social_security.service;

import com.hrm.model.social_security.Archive;
import com.hrm.model.social_security.ArchiveDetail;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:52
 * @Description: 社保归档业务处理接口
 */
public interface ArchiveService {
    /**
     * 查询月份数据报表
     *
     * @param companyId
     * @param yearMonth
     * @param opType
     * @return
     */
    List<ArchiveDetail> historysDetail(String companyId, String yearMonth, int opType);

    /**
     * 数据归档
     *
     * @param yearMonth
     * @param companyId
     */
    void archive(String yearMonth, String companyId);

    /**
     * 查询历史归档列表
     *
     * @param companyId
     * @param year
     * @return
     */
    List<Archive> findArchiveByYear(String companyId, String year);

    /**
     * 根据用户id和考勤年月查询用户考勤归档明细
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    ArchiveDetail findUserArchiveDetail(String userId, String yearMonth);
}
