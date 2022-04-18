package com.hrm.getway;

import com.hrm.common.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 09:27
 * @Description: 网关服务启动类
 */
//声明boot工程
@SpringBootApplication(scanBasePackages = "com.hrm")
//开启zuul网关功能
@EnableZuulProxy
//开启服务发现功能
@EnableDiscoveryClient
public class GetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class);
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
