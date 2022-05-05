package com.hrm.api.salary;

import com.hrm.core.pojo.PageResult;
import com.hrm.core.pojo.Result;
import com.hrm.model.salary.dto.UserSalaryItemDto;
import com.hrm.model.salary.entity.UserSalary;
import com.hrm.model.salary.vo.UserSalaryItemVo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 12:41
 * @Description: 薪酬Api接口
 */
@RequestMapping("/salarys")
public interface SalaryControllerApi {
    /**
     * 查询用户薪资
     *
     * @param userId
     * @return
     */
    @GetMapping("/modify/{userId}")
    Result<UserSalary> modifyGet(@PathVariable("userId") String userId);

    /**
     * 调薪
     *
     * @param userSalary
     * @return
     */
    @PostMapping("/modify/{userId}")
    Result<Object> modify(@PathVariable("userId") String userId, @RequestBody UserSalary userSalary);

    /**
     * 定薪
     *
     * @param userSalary
     * @return
     */
    @PostMapping("/init/{userId}")
    Result<Object> init(@RequestBody UserSalary userSalary);

    /**
     * 查询列表
     *
     * @param userSalaryItemDto
     * @return
     */
    @PostMapping("/list")
    Result<PageResult<UserSalaryItemVo>> list(@RequestBody UserSalaryItemDto userSalaryItemDto);
}
