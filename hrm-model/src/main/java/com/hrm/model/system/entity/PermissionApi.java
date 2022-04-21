package com.hrm.model.system.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:13
 * @Description: api资源实体类
 */
@Data
@Entity
@Table(name = "pe_permission_api")
public class PermissionApi implements Serializable {
    private static final long serialVersionUID = 5099274642177603255L;
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 链接
     */
    private String apiUrl;
    /**
     * 请求类型
     */
    private String apiMethod;
    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    private String apiLevel;
}
