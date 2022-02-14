package com.hrm.domain.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-13 21:45
 * @Description: 用户角色关联数据接收类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = 9127596054407069769L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 角色id集合
     */
    private List<String> roleIds;
}
