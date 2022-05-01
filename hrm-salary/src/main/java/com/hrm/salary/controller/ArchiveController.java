package com.hrm.salary.controller;

import com.hrm.api.salary.ArchiveControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.Result;
import com.hrm.model.salary.entity.SalaryArchiveDetail;
import com.hrm.salary.service.ArchiveService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:31
 * @Description: 归档控制器
 */
@CrossOrigin
@RestController
public class ArchiveController extends BaseController implements ArchiveControllerApi {
    private final ArchiveService archiveService;

    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Override
    public Result<List<SalaryArchiveDetail>> historyDetail(@PathVariable String yearMonth, @RequestParam("opType") int opType) {
        List<SalaryArchiveDetail> list = this.archiveService.getHistoryDetail(super.companyId, yearMonth, opType);
        return Result.success(list);
    }
}
