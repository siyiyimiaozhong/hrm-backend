package com.hrm.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-02-07 11:03
 * @Description: spring上下文工具类
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    /**
     * 通过名字获取上下文中的bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类型获取上下文中的bean
     *
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
