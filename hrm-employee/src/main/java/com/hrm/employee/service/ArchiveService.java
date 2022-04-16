package com.hrm.employee.service;

import com.hrm.core.entity.PageResult;
import com.hrm.model.employee.Archive;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 07:49
 * @Description: 月度员工归档业务层接口
 */
public interface ArchiveService {
    /**
     * 获取归档信息列表
     *
     * @param companyId
     * @param pageSize
     * @param page
     * @param year
     * @return
     */
    PageResult<Archive> list(String companyId, Integer pageSize, Integer page, String year);
}
