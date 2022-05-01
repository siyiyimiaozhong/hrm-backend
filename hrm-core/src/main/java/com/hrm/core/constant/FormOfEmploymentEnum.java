package com.hrm.core.constant;

import org.apache.commons.lang.StringUtils;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-30 09:59
 * @Description: 聘用形式枚举
 */
public enum FormOfEmploymentEnum {
    OFFICIAL(1, "正式"), FIELDWORK(2, "实习"), SERVICE(3, "劳务"), QUESTION(4, "疑问"),REHIRING(5,"返聘"),OUTSOURCING(6,"外包");

    private final Integer key;
    private final String value;

    FormOfEmploymentEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static FormOfEmploymentEnum getEnumByKey(Integer key) {
        if (null == key) {
            return null;
        }
        for (FormOfEmploymentEnum temp : FormOfEmploymentEnum.values()) {
            if (temp.getKey().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    public static FormOfEmploymentEnum getEnumByValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (FormOfEmploymentEnum temp : FormOfEmploymentEnum.values()) {
            if (temp.getValue().equals(value)) {
                return temp;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
