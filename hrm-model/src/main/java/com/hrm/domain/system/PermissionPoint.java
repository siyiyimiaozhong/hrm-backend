package com.hrm.domain.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:10
 * @Description: 标签/按钮资源实体类
 */
@Data
@Entity
@Table(name = "pe_permission_point")
public class PermissionPoint implements Serializable {
    private static final long serialVersionUID = -2767725230497838238L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 权限代码
     */
    private String pointClass;

    private String pointIcon;

    private String pointStatus;
}
