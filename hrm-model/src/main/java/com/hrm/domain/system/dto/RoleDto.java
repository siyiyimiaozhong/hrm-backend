package com.hrm.domain.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:52
 * @Description: 角色数据接收实体类
 */
@Data
public class RoleDto implements Serializable {

    private static final long serialVersionUID = 357905013060169296L;
    /**
     * 角色id
     */
    private String id;
    /**
     * 权限id集合
     */
    private List<String> permIds;
}
