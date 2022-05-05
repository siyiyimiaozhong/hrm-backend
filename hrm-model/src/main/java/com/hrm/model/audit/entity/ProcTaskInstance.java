package com.hrm.model.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 13:50
 * @Description: 业务流程的任务表（每个节点的审批意见，状态等）
 */
@Entity
@Table(name = "proc_task_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcTaskInstance implements Serializable {
    private static final long serialVersionUID = 1197039778202034930L;

    /**
     * 任务实例ID
     */
    @Id
    private String taskId;
    /**
     * 处理意见
     */
    private String handleOpinion;
    /**
     * 处理时间
     */
    private Date handleTime;
    /**
     * 处理类型（2审批通过；3审批不通过；4撤销）
     */
    private String handleType;
    /**
     * 实际处理用户ID
     */
    private String handleUserId;
    /**
     * 实际处理用户
     */
    private String handleUserName;
    /**
     * 流程实例ID
     */
    private String processId;
    /**
     * 任务节点key
     */
    private String taskKey;
    /**
     * 任务节点
     */
    private String taskName;
}