package com.hrm.api.employee;

import com.hrm.core.entity.Result;
import com.hrm.model.employee.Positive;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 16:38
 * @Description: 转正Api
 */
public interface PositiveControllerApi {
    /**
     * 转正表单保存
     *
     * @param userId
     * @param positive
     * @return
     */
    Result<Object> savePositive(@PathVariable("id") String userId, @RequestBody Positive positive);

    /**
     * 转正信息获取
     *
     * @param userId
     * @return
     */
    Result<Object> get(@PathVariable("id") String userId);
}
