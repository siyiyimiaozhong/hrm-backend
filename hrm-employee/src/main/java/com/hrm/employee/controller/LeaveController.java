package com.hrm.employee.controller;

import com.hrm.api.employee.LeaveControllerApi;
import com.hrm.core.entity.Result;
import com.hrm.employee.service.ResignationService;
import com.hrm.model.employee.Resignation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-18 21:44
 * @Description: 离职控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/employees/leave")
public class LeaveController implements LeaveControllerApi {

    private final ResignationService resignationService;

    public LeaveController(ResignationService resignationService) {
        this.resignationService = resignationService;
    }

    /**
     * 离职表单保存
     *
     * @param userId
     * @param resignation
     * @return
     * @throws Exception
     */
    @PostMapping("/{id}")
    @Override
    public Result<Object> saveLeave(@PathVariable("id") String userId, @RequestBody Resignation resignation) {
        this.resignationService.save(userId, resignation);
        return Result.success();
    }

    /**
     * 离职信息读取
     *
     * @param uid
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    @Override
    public Result<Resignation> findLeave(@PathVariable("id") String uid) {
        Resignation resignation = resignationService.get(uid);
        return Result.success(resignation);
    }
}
