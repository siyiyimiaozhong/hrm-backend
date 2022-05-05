package com.hrm.audit.service.impl;

import com.hrm.audit.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 18:03
 * @Description: 流程业务层实现类
 */
@Service
public class ProcessServiceImpl implements ProcessService {

    private final RepositoryService repositoryService;

    public ProcessServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public void deployProcess(MultipartFile file, String companyId) {
        //1.获取上传的文件名
        String fileName = file.getOriginalFilename();
        //2.通过repositoryService进行流程部署
        //DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        //文件名称,文件的bytes数组
        //deploymentBuilder.addBytes(fileName,file.getBytes()); //部署流程
        //deploymentBuilder.tenantId(companyId);
        DeploymentBuilder deploymentBuilder = null;
        try {
            deploymentBuilder = repositoryService.createDeployment().addBytes(fileName, file.getBytes()).tenantId(companyId);
            Deployment deploy = deploymentBuilder.deploy();
            //3.打印部署结果
            System.out.println(deploy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProcessDefinition> getProcessDefinitionList(String companyId) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(companyId)
                .latestVersion().list();
    }

    @Override
    public void suspendProcess(String processKey, String companyId) {
        //1.根据processKey查询流程定义
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processKey)
                .processDefinitionTenantId(companyId).latestVersion().singleResult();
        //2.判断是否为挂起状态
        if (definition.isSuspended()) {
            //2.1 如果是挂起状态:设置为激活
            repositoryService.activateProcessDefinitionById(definition.getId());
        } else {
            //2.2 如果不是激活状态: 设置为挂起
            repositoryService.suspendProcessDefinitionById(definition.getId());
        }
    }
}
