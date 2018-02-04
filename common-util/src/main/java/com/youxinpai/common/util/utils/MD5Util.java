package com.youxinpai.common.util.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5工具类
 * Created by Xs on 2018/1/30.
 */
public class MD5Util {

    /**
     * MD5 转换字节数组为16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String md5DigestAsHex(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }
}
