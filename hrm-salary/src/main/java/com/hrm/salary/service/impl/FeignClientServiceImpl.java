package com.hrm.salary.service.impl;

import com.alibaba.fastjson.JSON;
import com.hrm.core.pojo.Result;
import com.hrm.model.attendance.entity.ArchiveMonthlyInfo;
import com.hrm.model.social_security.entity.ArchiveDetail;
import com.hrm.salary.client.AttendanceFeignClient;
import com.hrm.salary.client.SocialSecurityFeignClient;
import com.hrm.salary.service.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-26 07:24
 * @Description: fegin远程调用业务层实现类
 */
@Service
public class FeignClientServiceImpl implements FeignClientService {

    private final AttendanceFeignClient attendanceFeignClient;
    private final SocialSecurityFeignClient socialFeignClient;

    public FeignClientServiceImpl(AttendanceFeignClient attendanceFeignClient, SocialSecurityFeignClient socialFeignClient) {
        this.attendanceFeignClient = attendanceFeignClient;
        this.socialFeignClient = socialFeignClient;
    }

    @Override
    public ArchiveMonthlyInfo getAtteInfo(String userId, String yearMonth) {
        Result<ArchiveMonthlyInfo> result = this.attendanceFeignClient.historyData(userId, yearMonth);
        ArchiveMonthlyInfo info = null;
        if (result.isSuccess()) {
            info = JSON.parseObject(JSON.toJSONString(result.getData()), ArchiveMonthlyInfo.class);
        }
        return info;
    }

    @Override
    public ArchiveDetail getSocialInfo(String userId, String yearMonth) {
        Result<ArchiveDetail> result = this.socialFeignClient.historysData(userId, yearMonth);
        ArchiveDetail info = null;
        if (result.isSuccess()) {
            info = JSON.parseObject(JSON.toJSONString(result.getData()), ArchiveDetail.class);
        }
        return info;
    }
}
