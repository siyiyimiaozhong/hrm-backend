package com.hrm.api.employee;

import com.hrm.core.pojo.Result;
import com.hrm.model.employee.entity.Positive;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:38
 * @Description: 转正Api
 */
@RequestMapping("/employees/positive")
public interface PositiveControllerApi {
    /**
     * 转正表单保存
     *
     * @param userId
     * @param positive
     * @return
     */
    @PostMapping("/{id}")
    Result<Object> savePositive(@PathVariable("id") String userId, @RequestBody Positive positive);

    /**
     * 转正信息获取
     *
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    Result<Object> get(@PathVariable("id") String userId);
}
