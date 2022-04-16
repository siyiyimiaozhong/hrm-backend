package com.hrm.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-03-20 09:27
 * @Description: 网关服务启动类
 */
// 开启eureka服务端服务
@EnableEurekaServer
@SpringBootApplication
public class GetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class);
    }
}
