package com.hrm.system.config;

import com.hrm.common.interceptor.JwtInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-20 16:04
 * @Description: 拦截器配置
 */
//@Configuration
public class SystemConfig extends WebMvcConfigurationSupport {
    private final JwtInterceptor jwtInterceptor;

    public SystemConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //1.添加自定义拦截器
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")//2.指定拦截器的url地址
                .excludePathPatterns("/sys/login", "/frame/register/**");//3.指定不拦截的url地址
    }
}
