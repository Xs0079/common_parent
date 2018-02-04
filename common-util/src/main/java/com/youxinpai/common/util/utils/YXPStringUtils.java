package com.youxinpai.common.util.utils;

/**
 * 常用string 操作工具类
 * Created by Xs on 2018/1/31.
 */
public class YXPStringUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }

        return String.valueOf(obj);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}
