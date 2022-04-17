package com.hrm.core.constant;

import lombok.Getter;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-10 17:14
 * @Description: 文件导入模板枚举
 */
@Getter
public enum FileImportTemplateEnum {
    /**
     * 用户信息导入模板
     */
    USER_EXCEL("用户信息导入模板.xlsx", "templates/excel/用户信息导入模板.xlsx"),

    ;

    private String name;
    private String path;

    FileImportTemplateEnum(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
