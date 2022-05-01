package com.hrm.salary.service;

import com.hrm.model.salary.entity.SalaryArchive;
import com.hrm.model.salary.entity.SalaryArchiveDetail;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:35
 * @Description: 工资归档业务层接口
 */
public interface ArchiveService {

    /**
     * 制作薪资报表
     *
     * @param companyId
     * @param yearMonth
     * @param opType    1:新制作的报表,其他:查询已归档的报表数据
     * @return
     */
    List<SalaryArchiveDetail> getHistoryDetail(String companyId, String yearMonth, int opType);

    /**
     * 根据企业和年月查询归档主表数据
     *
     * @param yearMonth
     * @param companyId
     * @return
     */
    SalaryArchive findSalaryArchive(String yearMonth, String companyId);

    /**
     * 根据归档的id查询所有的归档明细记录
     *
     * @param id
     * @return
     */
    List<SalaryArchiveDetail> findSalaryArchiveDetail(String id);

    /**
     * 查询月报表数据
     * 计算薪资 : 社保和考勤已经归档
     *
     * @param yearMonth
     * @param companyId
     * @return
     */
    List<SalaryArchiveDetail> getReports(String yearMonth, String companyId);
}
