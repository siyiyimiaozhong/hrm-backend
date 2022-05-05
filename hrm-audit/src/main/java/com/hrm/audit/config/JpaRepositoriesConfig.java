package com.hrm.audit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-05-03 11:09
 * @Description: JPA配置类
 */
@Configuration
@EnableJpaRepositories(
        //代理的dao接口所在的包
        basePackages = "com.hrm.audit.dao",
        entityManagerFactoryRef = "hrmEntityManager",
        transactionManagerRef = "hrmTransactionManager"
)
public class JpaRepositoriesConfig {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("hrmDataSource")
    private DataSource hrmDataSource;

    /**
     * 创建entityManagerFactory工厂
     *
     * @return
     */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean hrmEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(hrmDataSource);
        //配置扫描的实体类包
        em.setPackagesToScan("com.hrm.model.audit.entity", "com.hrm.model.system.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        properties.put("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        em.setJpaPropertyMap(properties);
        return em;
    }

    /**
     * 创建事务管理器
     *
     * @return
     */
    @Primary
    @Bean
    public PlatformTransactionManager hrmTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(hrmEntityManager().getObject());
        return transactionManager;
    }
}
