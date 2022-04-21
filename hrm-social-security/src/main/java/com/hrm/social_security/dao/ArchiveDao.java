package com.hrm.social_security.dao;

import com.hrm.model.social_security.entity.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 20:19
 * @Description: 社保-归档持久层接口
 */
public interface ArchiveDao extends JpaRepository<Archive, String>, JpaSpecificationExecutor<Archive> {

    Archive findByCompanyIdAndYearsMonth(String companyId, String yearMonth);

    List<Archive> findByCompanyIdAndYearsMonthLike(String companyId, String yearsMonth);
}