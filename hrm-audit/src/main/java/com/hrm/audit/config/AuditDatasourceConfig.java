package com.hrm.audit.config;

import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 11:08
 * @Description: 多数据源配置类
 *
 *  多数据源配置类
 *      1.activiti数据库连接池
 *          默认
 *      2.HRM业务数据库连接池
 *          明确指定 hrmDataSource
 */
@Configuration
public class AuditDatasourceConfig extends AbstractProcessEngineAutoConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.act")
    @Qualifier("activitiDataSource")
    public DataSource activitiDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hrm")
    @Qualifier("hrmDataSource")
    public DataSource hrmDataSource() {
        return DataSourceBuilder.create().build();
    }
}
