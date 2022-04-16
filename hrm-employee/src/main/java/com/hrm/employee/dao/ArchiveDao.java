package com.hrm.employee.dao;

import com.hrm.model.employee.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:31
 * @Description: 月度员工归档持久层
 */
public interface ArchiveDao extends JpaRepository<Archive, String>, JpaSpecificationExecutor<Archive> {
    @Query(value = "SELECT * FROM em_archive WHERE company_id = ?1 AND month = ?2 ORDER BY create_time DESC LIMIT 1;", nativeQuery = true)
    Archive findByLast(String companyId, String month);

    @Query(value = "SELECT * FROM em_archive WHERE company_id = ?1 AND month LIKE ?2 GROUP BY month HAVING MAX(create_time) limit ?3,?4", nativeQuery = true)
    List<Archive> findAllData(String companyId, String year, Integer index, Integer pagesize);

    @Query(value = "SELECT count(DISTINCT month) FROM em_archive WHERE company_id = ?1 AND month LIKE ?2", nativeQuery = true)
    long countAllData(String companyId, String year);
}