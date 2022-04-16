package com.hrm.common.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-16 19:58
 * @Description: 七牛云配置读取类
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiniuyunConfig {
    @Value("${qiniuyun.bucket}")
    private String bucket;
    @Value("${qiniuyun.domain-name}")
    private String domainName;
    @Value("${qiniuyun.access-key}")
    private String accessKey;
    @Value("${qiniuyun.secret-key}")
    private String secretKey;
}
