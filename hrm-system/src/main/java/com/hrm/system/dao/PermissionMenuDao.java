package com.hrm.system.dao;

import com.hrm.domain.system.PermissionMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-10 06:44
 * @Description: 菜单资源持久层接口
 */
public interface PermissionMenuDao extends JpaRepository<PermissionMenu, String>, JpaSpecificationExecutor<PermissionMenu> {
}
