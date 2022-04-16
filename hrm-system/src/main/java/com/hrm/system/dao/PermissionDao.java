package com.hrm.system.dao;

import com.hrm.model.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:42
 * @Description: 权限持久层接口
 */
public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {
    /**
     * 根据父id和类型查询API权限列表
     *
     * @param type
     * @param pid
     * @return
     */
    List<Permission> findByTypeAndPid(int type, String pid);
}
