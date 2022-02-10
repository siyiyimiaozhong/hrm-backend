package com.hrm.domain.system;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 18:11
 * @Description: 菜单资源实体类
 */
@Data
@Entity
@Table(name = "pe_permission_menu")
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = 7113318664347445404L;
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 展示图标
     */
    private String menuIcon;

    /**
     * 排序号
     */
    private String menuOrder;
}
