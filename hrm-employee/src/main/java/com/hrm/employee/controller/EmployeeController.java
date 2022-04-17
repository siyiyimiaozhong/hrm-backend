package com.hrm.employee.controller;

import com.hrm.api.employee.EmployeeControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.entity.Result;
import com.hrm.employee.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:30
 * @Description: 员工信息控制器
 */
@CrossOrigin
@RestController
public class EmployeeController extends BaseController implements EmployeeControllerApi {
    private final UserCompanyPersonalService userCompanyPersonalService;
    private final UserCompanyJobsService userCompanyJobsService;
    private final ResignationService resignationService;
    private final TransferPositionService transferPositionService;
    private final PositiveService positiveService;
    private final ArchiveService archiveService;


    public EmployeeController(UserCompanyPersonalService userCompanyPersonalService,
                              UserCompanyJobsService userCompanyJobsService,
                              ResignationService resignationService,
                              TransferPositionService transferPositionService,
                              PositiveService positiveService,
                              ArchiveService archiveService) {
        this.userCompanyPersonalService = userCompanyPersonalService;
        this.userCompanyJobsService = userCompanyJobsService;
        this.resignationService = resignationService;
        this.transferPositionService = transferPositionService;
        this.positiveService = positiveService;
        this.archiveService = archiveService;
    }


    /**
     * 导入员工信息
     *
     * @param attachment
     * @return
     * @throws Exception
     */
    @Override
    public Result<Object> importData(@RequestParam("file") MultipartFile attachment) {
        return Result.success();
    }

    @Override
    public void export(@PathVariable("month") String month, HttpServletResponse response) {
        this.userCompanyPersonalService.exportReport(response, super.companyId, month);
    }

    @Override
    public void exportPdf(@PathVariable("id") String id) {
        this.userCompanyPersonalService.exportProfilePdf(id, response);
    }
}
