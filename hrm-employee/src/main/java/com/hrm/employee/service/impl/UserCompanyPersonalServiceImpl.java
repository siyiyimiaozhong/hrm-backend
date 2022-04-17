package com.hrm.employee.service.impl;

import com.hrm.common.properties.QiniuyunConfig;
import com.hrm.common.utils.BeanMapUtils;
import com.hrm.common.utils.ExcelExportUtil;
import com.hrm.core.constant.ResultCode;
import com.hrm.core.exception.BusinessException;
import com.hrm.core.template.EmployeeReportTemplate;
import com.hrm.employee.dao.UserCompanyJobsDao;
import com.hrm.employee.dao.UserCompanyPersonalDao;
import com.hrm.employee.service.UserCompanyPersonalService;
import com.hrm.model.employee.UserCompanyJobs;
import com.hrm.model.employee.UserCompanyPersonal;
import com.hrm.model.employee.vo.EmployeeReportVo;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-19 14:58
 * @Description: 员工详细信息业务层实现类
 */
@Service
public class UserCompanyPersonalServiceImpl implements UserCompanyPersonalService {

    private final UserCompanyPersonalDao userCompanyPersonalDao;
    private final UserCompanyJobsDao userCompanyJobsDao;
    private final QiniuyunConfig qiniuyunConfig;

    public UserCompanyPersonalServiceImpl(UserCompanyPersonalDao userCompanyPersonalDao,
                                          UserCompanyJobsDao userCompanyJobsDao,
                                          QiniuyunConfig qiniuyunConfig) {
        this.userCompanyPersonalDao = userCompanyPersonalDao;
        this.userCompanyJobsDao = userCompanyJobsDao;
        this.qiniuyunConfig = qiniuyunConfig;
    }

    @Override
    public void save(String userId, String companyId, UserCompanyPersonal userCompanyPersonal) {
        userCompanyPersonal.setUserId(userId);
        userCompanyPersonal.setCompanyId(companyId);
        this.userCompanyPersonalDao.save(userCompanyPersonal);
    }

    @Override
    public UserCompanyPersonal get(String userId) {
        UserCompanyPersonal userCompanyPersonal = this.userCompanyPersonalDao.findByUserId(userId);
        if (null == userCompanyPersonal) {
            throw new BusinessException(ResultCode.PARAMETER_VALIDATION_FAILED);
        }
        return userCompanyPersonal;
    }

    @Override
    public void exportReport(HttpServletResponse response, String companyId, String month) {
        //1.获取报表数据
        List<EmployeeReportVo> list = this.userCompanyPersonalDao.findByReport(companyId, month + "%");
        List<EmployeeReportTemplate> templateList = list.stream().map(this::getEmployeeReportTemplate).collect(Collectors.toList());
        if (templateList.isEmpty()) {
            // 覆盖excel模板中模板数据
            templateList.add(new EmployeeReportTemplate());
        }
        ExcelExportUtil<EmployeeReportTemplate> templateExcelExportUtil = new ExcelExportUtil<>(EmployeeReportTemplate.class, 1, 1);
        try {
            Resource resource = new ClassPathResource("template/excel/".concat("人事报表模板.xlsx"));
            FileInputStream fis = new FileInputStream(resource.getFile());
            templateExcelExportUtil.export(response, fis, templateList, month + "人事报表.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportProfilePdf(String id, HttpServletResponse response) {
        try {
            //1.引入jasper文件
            Resource resource = new ClassPathResource("templates/pdf/profile.jasper");
            FileInputStream fis = new FileInputStream(resource.getFile());

            //2.构造数据
            //a.用户详情数据
            UserCompanyPersonal personal = this.userCompanyPersonalDao.findById(id).get();
            //b.用户岗位信息数据
            UserCompanyJobs jobs = this.userCompanyJobsDao.findById(id).get();
            //c.用户头像        域名 / id
            String staffPhoto = qiniuyunConfig.getDomainName() + id;

            //3.填充pdf模板数据，并输出pdf
            Map<String, Object> params = new HashMap<>();

            Map<String, Object> map1 = BeanMapUtils.beanToMap(personal);
            Map<String, Object> map2 = BeanMapUtils.beanToMap(jobs);

            params.putAll(map1);
            params.putAll(map2);
            params.put("staffPhoto", staffPhoto);

            ServletOutputStream os = response.getOutputStream();
            try {
                JasperPrint print = JasperFillManager.fillReport(fis, params, new JREmptyDataSource());
                JasperExportManager.exportReportToPdfStream(print, os);
            } finally {
                os.flush();
            }
        } catch (Exception e) {
            throw new BusinessException(ResultCode.FAILED_TO_EXPORT_PDF);
        }
    }

    private EmployeeReportTemplate getEmployeeReportTemplate(EmployeeReportVo employeeReportVo) {
        EmployeeReportTemplate template = new EmployeeReportTemplate();
        template.setUserId(employeeReportVo.getUserId());
        template.setUsername(employeeReportVo.getUsername());
        template.setMobile(employeeReportVo.getMobile());
        template.setTheHighestDegreeOfEducation(employeeReportVo.getTheHighestDegreeOfEducation());
        template.setNationalArea(employeeReportVo.getNationalArea());
        template.setPassportNo(employeeReportVo.getPassportNo());
        template.setNativePlace(employeeReportVo.getNativePlace());
        template.setBirthday(employeeReportVo.getBirthday());
        template.setZodiac(employeeReportVo.getZodiac());
        template.setTimeOfEntry(employeeReportVo.getTimeOfEntry());
        template.setTypeOfTurnover(employeeReportVo.getTypeOfTurnover());
        template.setReasonsForLeaving(employeeReportVo.getReasonsForLeaving());
        template.setResignationTime(employeeReportVo.getResignationTime());
        return template;
    }
}
