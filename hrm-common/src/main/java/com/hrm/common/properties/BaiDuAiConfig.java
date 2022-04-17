package com.hrm.common.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 11:41
 * @Description: 百度Ai人脸识别配置实体类
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiDuAiConfig {
    @Value("${baiduyun.app-id}")
    private String appId;
    @Value("${baiduyun.app-key}")
    private String apiKey;
    @Value("${baiduyun.secret-key}")
    private String secretKey;
    @Value("${baiduyun.image-type}")
    private String imageType;
    @Value("${baiduyun.group-id}")
    private String groupId;
}
