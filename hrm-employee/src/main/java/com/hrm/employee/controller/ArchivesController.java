package com.hrm.employee.controller;

import com.hrm.api.employee.ArchivesControllerApi;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.employee.service.ArchiveService;
import com.hrm.model.employee.entity.Archive;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 21:49
 * @Description: 归档控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/employees/archives")
public class ArchivesController extends BaseController implements ArchivesControllerApi {
    private final ArchiveService archiveService;

    public ArchivesController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    /**
     * 历史归档详情列表
     *
     * @param month
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/{month}")
    @Override
    public Result<Object> archives(@PathVariable("month") String month, @RequestParam(name = "type") Integer type) {
        return Result.success();
    }

    /**
     * 归档更新
     *
     * @param month
     * @return
     * @throws Exception
     */
    @PutMapping("/{month}")
    @Override
    public Result<Object> saveArchives(@PathVariable("month") String month) {
        return Result.success();
    }

    /**
     * 历史归档列表
     *
     * @param pageSize
     * @param page
     * @param year
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    @Override
    public Result<PageResult<Archive>> list(@RequestParam("pagesize") Integer pageSize, @RequestParam("page") Integer page, @RequestParam("year") String year) {
        PageResult<Archive> pr = this.archiveService.list(super.companyId, pageSize, page, year);
        return Result.success(pr);
    }
}
