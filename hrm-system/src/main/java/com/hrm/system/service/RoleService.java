package com.hrm.system.service;

import com.hrm.common.entity.PageResult;
import com.hrm.domain.system.Role;
import com.hrm.domain.system.dto.RoleDto;
import com.hrm.domain.system.vo.RoleVo;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:40
 * @Description: 角色业务层接口
 */
public interface RoleService {
    /**
     * 分配权限
     *
     * @param roleDto
     */
    void assignPerms(RoleDto roleDto);

    /**
     * 校验并添加角色
     *
     * @param role
     */
    void checkAndInsert(Role role);

    /**
     * 删除角色
     *
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    RoleVo findRoleVoById(String id);

    /**
     * 分页获取角色信息
     *
     * @param companyId
     * @param page
     * @param size
     * @return
     */
    PageResult<Role> page(String companyId, int page, int size);

    /**
     * 通过企业id查询所有角色
     *
     * @param companyId
     * @return
     */
    List<Role> findAllByCompanyId(String companyId);

    /**
     * 校验并修改角色信息
     *
     * @param id
     * @param role
     */
    void checkAndUpdate(String id, Role role);
}
