package com.hrm.employee.dao;

import com.hrm.model.employee.entity.UserCompanyPersonal;
import com.hrm.model.employee.vo.EmployeeReportVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:48
 * @Description: 员工详细信息持久层
 */
public interface UserCompanyPersonalDao extends JpaRepository<UserCompanyPersonal, String>, JpaSpecificationExecutor<UserCompanyPersonal> {
    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserCompanyPersonal findByUserId(String userId);

    /**
     * 根据企业id以及月份 查询用户报表细腻些
     *
     * @param companyId
     * @param month
     * @return
     */
    //SELECT * FROM em_user_company_personal a LEFT JOIN em_resignation b ON a.user_id = b.user_id
    //WHERE a.time_of_entry LIKE '2022-02%' OR (b.resignation_time LIKE "2022-02%")
    @Query(value = "select new com.hrm.model.employee.vo.EmployeeReportVo(a, b) from UserCompanyPersonal a " +
            "LEFT JOIN Resignation b on a.userId=b.userId where a.companyId=?1 and (a.timeOfEntry like?2 or " +
            "b.resignationTime like ?2)")
    List<EmployeeReportVo> findByReport(String companyId, String month);
}