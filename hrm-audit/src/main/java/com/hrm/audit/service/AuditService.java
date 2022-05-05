package com.hrm.audit.service;

import com.hrm.core.pojo.PageResult;
import com.hrm.model.audit.entity.ProcInstance;
import com.hrm.model.audit.entity.ProcTaskInstance;

import java.util.List;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 17:55
 * @Description: 审计业务层接口
 */
public interface AuditService {

    /**
     * 查询所有的业务的申请流程
     *
     * @param instance
     * @param page
     * @param size
     * @return
     */
    PageResult<ProcInstance> getInstanceList(ProcInstance instance, int page, int size);

    /**
     * 根据id查询申请数据响应
     *
     * @param id
     * @return
     */
    ProcInstance findInstanceDetail(String id);

    /**
     * 流程申请
     *
     * @param map
     * @param companyId
     */
    void startProcess(Map map, String companyId);

    /**
     * 提交审核
     * handleType; // 处理类型（2审批通过；3审批不通过；4撤销）
     * <p>
     * ProcTaskInstance
     * handleOpinion: "xxxxxxc"                   操作说明
     * handleType: "4"                            处理类型（2审批通过；3审批不通过；4撤销）
     * handleUserId: "1063705989926227968"        处理人
     * processId: "1175593305465352192"           业务流程id
     */
    void commit(ProcTaskInstance taskInstance, String companyId);

    /**
     * 根据processId查询业务流程明细
     *
     * @param id
     * @return
     */
    List<ProcTaskInstance> findTasksByProcess(String id);
}
