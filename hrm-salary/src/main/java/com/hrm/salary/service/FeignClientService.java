package com.hrm.salary.service;

import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.social_security.entity.ArchiveDetail;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 07:23
 * @Description: feign远程调用业务层接口
 */
public interface FeignClientService {

    /**
     * 考勤
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    ArchiveMonthlyInfo getAtteInfo(String userId, String yearMonth);

    /**
     * 社保
     *
     * @param userId
     * @param yearMonth
     * @return
     */
    ArchiveDetail getSocialInfo(String userId, String yearMonth);
}
