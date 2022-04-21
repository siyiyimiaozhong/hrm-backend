package com.hrm.model.system.vo;

import com.hrm.model.system.entity.Permission;
import com.hrm.model.system.entity.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-13 22:08
 * @Description: 角色信息Vo
 */
@Data
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 9056938159529427223L;

    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;

    private List<String> permIds = new LinkedList<>();

    public RoleVo(Role role) {
        BeanUtils.copyProperties(role, this);
        for (Permission perm : role.getPermissions()) {
            this.permIds.add(perm.getId());
        }
    }
}
