package com.hrm.company;

import com.hrm.common.utils.IdWorker;
import com.hrm.common.utils.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-01-14 6:35
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.hrm")
@EntityScan(value = "com.hrm.domain.company")
public class CompanyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CompanyApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
