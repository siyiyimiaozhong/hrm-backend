package com.hrm.employee.controller;

import com.hrm.api.employee.PositiveControllerApi;
import com.hrm.core.entity.Result;
import com.hrm.employee.service.PositiveService;
import com.hrm.model.employee.Positive;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 21:47
 * @Description: 转正控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/employees/positive")
public class PositiveController implements PositiveControllerApi {
    private final PositiveService positiveService;

    public PositiveController(PositiveService positiveService) {
        this.positiveService = positiveService;
    }

    /**
     * 转正表单保存
     *
     * @param userId
     * @param positive
     * @return
     * @throws Exception
     */
    @PostMapping("/{id}")
    @Override
    public Result<Object> savePositive(@PathVariable("id") String userId, @RequestBody Positive positive) {
        this.positiveService.save(userId, positive);
        return Result.success();
    }

    /**
     * 转正信息获取
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    @Override
    public Result<Object> get(@PathVariable("id") String userId) {
        Positive positive = positiveService.get(userId);
        return Result.success(positive);
    }
}
