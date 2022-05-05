package com.hrm.audit.controller;

import com.hrm.api.audit.ProcessControllerApi;
import com.hrm.audit.service.AuditService;
import com.hrm.audit.service.ProcessService;
import com.hrm.common.controller.BaseController;
import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.audit.entity.ProcInstance;
import com.hrm.model.audit.entity.ProcTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:21
 * @Description: 流程控制器
 */
@CrossOrigin
@RestController
public class ProcessController extends BaseController implements ProcessControllerApi {

    private final ProcessService processService;
    private final AuditService auditService;

    public ProcessController(ProcessService processService, AuditService auditService) {
        this.processService = processService;
        this.auditService = auditService;
    }

    @Override
    public Result<Object> deployProcess(@RequestParam("file") MultipartFile file) {
        this.processService.deployProcess(file, super.companyId);
        return Result.success();
    }

//    @Override
//    public Result<List<ProcessDefinition>> definitionList() {
//        List<ProcessDefinition> list = this.processService.getProcessDefinitionList(super.companyId);
//        return Result.success(list);
//    }

    @Override
    public Result<Object> suspendProcess(@PathVariable("processKey") String processKey) {
        this.processService.suspendProcess(processKey, super.companyId);
        return Result.success();
    }

    @Override
    public Result<PageResult<ProcInstance>> instanceList(@RequestBody ProcInstance instance, @PathVariable("page") int page, @PathVariable("size") int size) {
        PageResult<ProcInstance> pr = this.auditService.getInstanceList(instance, page, size);
        return Result.success(pr);
    }

    @Override
    public Result<ProcInstance> instanceDetail(@PathVariable("id") String id) {
        ProcInstance instance = this.auditService.findInstanceDetail(id);
        return Result.success(instance);
    }

    @Override
    public Result<Object> startProcess(@RequestBody Map<String, Object> map) {
        this.auditService.startProcess(map, super.companyId);
        return Result.success();
    }

    @Override
    public Result<Object> commit(@RequestBody ProcTaskInstance taskInstance) {
        this.auditService.commit(taskInstance, super.companyId);
        return Result.success();
    }

    @Override
    public Result<List<ProcTaskInstance>> tasks(@PathVariable("id") String id) {
        List<ProcTaskInstance> tasksByProcess = auditService.findTasksByProcess(id);
        return Result.success(tasksByProcess);
    }
}
