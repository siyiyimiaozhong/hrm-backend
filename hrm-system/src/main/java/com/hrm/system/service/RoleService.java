package com.hrm.system.service;

import com.hrm.common.entity.PageResult;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.dto.RoleDto;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:40
 * @Description: 角色业务层接口
 */
public interface RoleService {
    /**
     * 分配权限
     * @param roleDto
     */
    void assignPerms(RoleDto roleDto);

    void checkAndInsert(Role role);

    void delete(String[] ids);

    Role findById(String id);

    PageResult<Role> page(String companyId, int page, int size);

    List<Role> findAllByCompanyId(String companyId);
}
