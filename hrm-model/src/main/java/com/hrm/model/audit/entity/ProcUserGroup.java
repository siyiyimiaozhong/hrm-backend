package com.hrm.model.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 13:51
 * @Description: 业务流程的用户组表
 */
@Entity
@Table(name = "proc_user_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcUserGroup {
    private static final long serialVersionUID = -9084332495284489553L;
    @Id
    private String id;
    private String name;
    private String param;
    private String isql;
}
