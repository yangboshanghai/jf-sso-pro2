package com.ouyeelf.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @className: StringUtil
 * @description：字符串工具类
 * @author：徐波
 * @date: Created in 2022/5/4 22:00
 */
public class StringUtil {
    /**
     * @Description 将字符串yyyyMMdd格式转换为yyyy-MM-dd
     * @Param str 日期字符串  yyyyMMdd
     * @Data 2022/5/4 22:05
     * @Return java.lang.String
     */
    public static String getFormatBirth(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }
        if (str.length() != 8) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, 4));
        sb.append("-");
        sb.append(str.substring(4, 6));
        sb.append("-");
        sb.append(str.substring(6, 8));
        return sb.toString();
    }

    /**
     * @Description 判断字符串是否为空
     * @Param o 字符串
     * @Data 2022/8/5 9:22
     * @Return java.lang.Boolean
     */
    public static Boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (StringUtils.isBlank(o.toString())) {
            return true;
        }
        return false;
    }
}
