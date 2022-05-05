package com.hrm.social_security;

import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-17 18:55
 * @Description: 社保模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.hrm", exclude = {SecurityAutoConfiguration.class})
@EntityScan(value = "com.hrm.model")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class SocialSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialSecurityApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    //解决no session
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
