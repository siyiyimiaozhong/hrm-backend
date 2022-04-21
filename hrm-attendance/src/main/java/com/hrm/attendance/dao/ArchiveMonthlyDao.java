package com.hrm.attendance.dao;

import com.hrm.model.attendance.entity.ArchiveMonthly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-21 00:43
 * @Description: 归档信息持久层接口
 */
public interface ArchiveMonthlyDao extends PagingAndSortingRepository<ArchiveMonthly,String>, CrudRepository<ArchiveMonthly,String>, JpaRepository<ArchiveMonthly,String>, JpaSpecificationExecutor<ArchiveMonthly> {


    /**
     * 查询某一年的归档列表
     * @param companyId
     * @param archiveYear
     * @return
     */
    List<ArchiveMonthly> findByCompanyIdAndArchiveYear(String companyId, String archiveYear);
}