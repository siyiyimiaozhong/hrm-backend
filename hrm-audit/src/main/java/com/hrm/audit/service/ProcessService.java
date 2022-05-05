package com.hrm.audit.service;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:00
 * @Description: 流程业务层接口
 */
public interface ProcessService {
    /**
     * 流程部署
     *
     * @param file      上传bpmn文件
     * @param companyId 企业id
     */
    void deployProcess(MultipartFile file, String companyId);

    /**
     * 根据企业id查询所有的流程定义对象
     *
     * @param companyId
     * @return
     */
    List<ProcessDefinition> getProcessDefinitionList(String companyId);

    /**
     * 挂起或者激活流程
     *
     * @param processKey
     * @param companyId
     */
    void suspendProcess(String processKey, String companyId);
}
