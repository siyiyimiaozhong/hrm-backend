package com.hrm.audit.dao;

import com.hrm.model.audit.entity.ProcInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 13:51
 * @Description:
 */
public interface ProcInstanceDao extends JpaRepository<ProcInstance,String>, JpaSpecificationExecutor<ProcInstance> {
}