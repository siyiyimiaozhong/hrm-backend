package com.hrm.api.audit;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.audit.entity.ProcInstance;
import com.hrm.model.audit.entity.ProcTaskInstance;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:05
 * @Description: 审计API接口
 */
@RequestMapping("/user/process")
public interface ProcessControllerApi {
    /**
     * 部署新流程
     * 前端将绘制好的流程模型图(bpmn)文件上传到方法中
     * 参数 : 上传的文件
     * MultipartFile
     *
     * @param file
     * @return
     */
    @PostMapping("/deploy")
    Result<Object> deployProcess(@RequestParam("file") MultipartFile file);

    /**
     * 查询所有的流程定义
     *
     * @return
     */
//    @GetMapping("/definition")
//    Result<List<ProcessDefinition>> definitionList();

    /**
     * 设置流程的挂起与激活状态
     *
     * @param processKey
     * @return
     */
    @GetMapping("/suspend/{processKey}")
    Result<Object> suspendProcess(@PathVariable("processKey") String processKey);

    /**
     * 查询申请列表
     * 业务参数:
     * 审批类型
     * 审批状态(多个,每个状态之间使用","隔开)
     * 当前节点的待处理人
     *
     * @param instance
     * @param page
     * @param size
     * @return
     */
    @PutMapping("/instance/{page}/{size}")
    Result<PageResult<ProcInstance>> instanceList(@RequestBody ProcInstance instance, @PathVariable("page") int page, @PathVariable("size") int size);

    /**
     * 查询申请的详情数据
     * 参数 : 申请对象的id
     *
     * @param id
     * @return
     */
    @GetMapping("/instance/{id}")
    Result<ProcInstance> instanceDetail(@PathVariable("id") String id);


    /**
     * 流程申请
     *
     * @param map
     * @return
     */
    @PostMapping("/startProcess")
    Result<Object> startProcess(@RequestBody Map<String, Object> map);

    /**
     * 提交审核
     * handleType; // 处理类型（2审批通过；3审批不通过；4撤销）
     *
     * @param taskInstance
     * @return
     */
    @PutMapping("/instance/commit")
    Result<Object> commit(@RequestBody ProcTaskInstance taskInstance);

    /**
     * 查询流程任务明细
     *
     * @param id
     * @return
     */
    @GetMapping("/instance/tasks/{id}")
    Result<List<ProcTaskInstance>> tasks(@PathVariable("id") String id);
}
