package com.hrm.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-06 21:32
 * @Description: Excel导入导出字段标识
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAttribute {
    /**
     * 对应的列名称
     */
    String name() default "";

    /**
     * 列序号
     */
    int sort();

    /**
     * 字段类型对应的格式
     */
    String format() default "";
}
