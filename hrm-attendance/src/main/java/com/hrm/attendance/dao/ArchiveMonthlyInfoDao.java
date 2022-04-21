package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 07:18
 * @Description: 归档信息详情持久层接口
 */
public interface ArchiveMonthlyInfoDao extends CrudRepository<ArchiveMonthlyInfo, String>, JpaRepository<ArchiveMonthlyInfo, String>, JpaSpecificationExecutor<ArchiveMonthlyInfo> {


    /**
     * 根据归档列表查询月归档详情
     *
     * @param atteArchiveMonthlyId
     * @return
     */
    List<ArchiveMonthlyInfo> findByAtteArchiveMonthlyId(String atteArchiveMonthlyId);

    /**
     * 根据用户id和年月查询归档明细
     *
     * @param userId
     * @param archiveDate
     * @return
     */
    ArchiveMonthlyInfo findByUserIdAndArchiveDate(String userId, String archiveDate);
}
