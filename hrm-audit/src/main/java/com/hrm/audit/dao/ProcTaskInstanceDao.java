package com.hrm.audit.dao;

import com.hrm.model.audit.entity.ProcTaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 13:52
 * @Description:
 */
public interface ProcTaskInstanceDao extends JpaRepository<ProcTaskInstance, String>, JpaSpecificationExecutor<ProcTaskInstance> {
    /**
     * 根据流程id查询
     * 展示每个节点数据
     *
     * @param processId
     * @return
     */
    List<ProcTaskInstance> findByProcessId(String processId);
}