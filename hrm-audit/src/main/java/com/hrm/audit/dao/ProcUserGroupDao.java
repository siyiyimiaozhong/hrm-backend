package com.hrm.audit.dao;

import com.hrm.model.audit.entity.ProcUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 13:52
 * @Description:
 */
public interface ProcUserGroupDao extends JpaRepository<ProcUserGroup, String>, JpaSpecificationExecutor<ProcUserGroup> {
}
