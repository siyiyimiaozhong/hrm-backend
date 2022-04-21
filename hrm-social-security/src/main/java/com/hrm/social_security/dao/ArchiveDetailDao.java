package com.hrm.social_security.dao;

import com.hrm.model.social_security.entity.ArchiveDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:47
 * @Description: 社保-归档明细持久层接口
 */
public interface ArchiveDetailDao extends JpaRepository<ArchiveDetail, String>, JpaSpecificationExecutor<ArchiveDetail> {

    void deleteByArchiveId(String archiveId);

    List<ArchiveDetail> findByArchiveId(String archiveId);

    //根据用户id和年月查询用户归档明细
    ArchiveDetail findByUserIdAndYearsMonth(String userId, String yearMonth);
}
