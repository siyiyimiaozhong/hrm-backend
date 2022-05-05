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
 * @CreateTime: Created in 2022-05-03 13:47
 * @Description: 业务流程实例表（请假业务的数据）
 */
@Entity
@Table(name = "proc_instance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcInstance implements Serializable {
    private static final long serialVersionUID = 1197039778202034930L;

    /**
     * 流程实例ID
     */
    @Id
    private String processId;
    /**
     * 流程定义ID
     */
    private String processDefinitionId;
    /**
     * 流程标识
     */
    private String processKey;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 流程状态（1审批中；2审批通过；3审批不通过；4撤销)
     */
    private String processState;
    /**
     * 申请人ID
     */
    private String userId;
    /**
     * 申请人
     */
    private String username;
    /**
     * 部门ID
     */
    private String departmentId;
    /**
     * 部门
     */
    private String departmentName;
    /**
     * 入职时间
     */
    private Date timeOfEntry;
    /**
     * 当前节点审批人ID
     */
    private String procCurrNodeUserId;
    /**
     * 当前节点审批人
     */
    private String procCurrNodeUserName;
    /**
     * 申请时间
     */
    private Date procApplyTime;
    /**
     * 结束流程时间
     */
    private Date procEndTime;
    /**
     * 申请的业务数据(需要转化为json)
     */
    private String procData;
}