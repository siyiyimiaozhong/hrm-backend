package com.hrm.salary.service;

import com.hrm.core.pojo.PageResult;
import com.hrm.model.salary.dto.UserSalaryItemDto;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.salary.vo.UserSalaryItemVo;

import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 00:53
 * @Description: 薪酬业务层接口
 */
public interface SalaryService {
    /**
     * 定薪或者调薪
     *
     * @param userSalary
     */
    void saveUserSalary(UserSalary userSalary);

    /**
     * 查询用户薪资
     *
     * @param userId
     * @return
     */
    UserSalary findUserSalary(String userId);

    /**
     * 分页查询当月薪资列表
     *
     * @param companyId
     * @param userSalaryItemDto
     * @return
     */
    PageResult<UserSalaryItemVo> findAll(String companyId, UserSalaryItemDto userSalaryItemDto);
}
