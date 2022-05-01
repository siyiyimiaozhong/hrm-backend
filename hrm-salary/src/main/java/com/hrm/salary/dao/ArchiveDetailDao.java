package com.hrm.salary.dao;

import com.hrm.model.salary.entity.SalaryArchiveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:30
 * @Description: 工资-归档详情表持久层接口
 */
public interface ArchiveDetailDao extends JpaRepository<SalaryArchiveDetail, String>, JpaSpecificationExecutor<SalaryArchiveDetail> {

    /**
     * 根据归档id查询归档详情
     * @param archiveId
     * @return
     */
    List<SalaryArchiveDetail> findByArchiveId(String archiveId);
}
