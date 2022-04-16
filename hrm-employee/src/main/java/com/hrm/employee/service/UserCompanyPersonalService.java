package com.hrm.employee.service;

import com.hrm.model.employee.UserCompanyPersonal;
import com.hrm.model.employee.vo.EmployeeReportVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:52
 * @Description: 员工详细信息业务层接口
 */
public interface UserCompanyPersonalService {
    /**
     * 保存员工详细信息
     *
     * @param userId
     * @param companyId
     * @param userCompanyPersonal
     */
    void save(String userId, String companyId, UserCompanyPersonal userCompanyPersonal);

    /**
     * 根据员工id获取员工信息
     *
     * @param userId
     * @return
     */
    UserCompanyPersonal get(String userId);

    /**
     * 导出员工报表
     * @param response
     * @param companyId
     * @param month
     */
    void exportReport(HttpServletResponse response, String companyId, String month);
}
