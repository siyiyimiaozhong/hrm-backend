package com.hrm.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-04-06 21:33
 * @Description: 通用工具类
 */
public class CommonUtils {
    /**
     * 将模糊查询得值中得空格和下滑线进行转义
     *
     * @param str
     * @return
     */
    public static String handleLikeStr(String str) {
        return "%" + str.replaceAll("\\\\", "\\\\\\\\")
                .replaceAll("_", "\\\\_")
                .replaceAll("%", "\\\\%")
                + "%";
    }

    /**
     * 判断对象为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        } else if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        } else if (object instanceof Map) {
            return ((Map) object).isEmpty();
        } else if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        }
        return false;
    }

    public static boolean isEmpty(Object... objects) {
        if (objects.length == 0) {
            return true;
        }
        for (Object object : objects) {
            if (isEmpty(object)) {
                return true;
            }
        }
        return false;
    }
}
