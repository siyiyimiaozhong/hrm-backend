package com.hrm.salary.dao;

import com.hrm.model.salary.entity.SalaryArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:29
 * @Description: 工资-归档表持久层接口
 */
public interface ArchiveDao extends JpaRepository<SalaryArchive,String>, JpaSpecificationExecutor<SalaryArchive> {

    /**
     * 根据企业id和年月查询归档信息
     * @param companyId
     * @param yearsMonth
     * @return
     */
    SalaryArchive findByCompanyIdAndYearsMonth(String companyId,String yearsMonth);
}
