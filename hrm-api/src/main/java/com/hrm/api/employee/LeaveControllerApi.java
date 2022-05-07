package com.hrm.api.employee;

import com.hrm.core.pojo.Result;
import com.hrm.model.employee.entity.Resignation;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:31
 * @Description: 离职Api
 */
@RequestMapping("/employees/leave")
public interface LeaveControllerApi {
    /**
     * 离职表单保存
     *
     * @param userId
     * @param resignation
     * @return
     */
    @PostMapping("/{id}")
    Result<Object> saveLeave(@PathVariable("id") String userId, @RequestBody Resignation resignation);

    /**
     * 离职信息读取
     *
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    Result<Resignation> findLeave(@PathVariable("id") String userId);
}
