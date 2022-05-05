package com.hrm.salary;

import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-25 09:13
 * @Description: 薪酬模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.hrm", exclude = {SecurityAutoConfiguration.class})
@EntityScan(value = "com.hrm.model")
@EnableFeignClients
public class SalaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalaryApplication.class, args);
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
