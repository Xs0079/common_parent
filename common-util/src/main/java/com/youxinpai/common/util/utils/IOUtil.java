package com.youxinpai.common.util.utils;

import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.youxinpai.common.util.constant.Constant.UTF8_CHARSET;

/**
 * io操作util类
 * Created by Xs on 2018/1/25.
 */
public class IOUtil {

    /**
     * inputstream 转字符串
     *
     * @param inputStream inputStream
     * @return string
     * @throws IOException IOException
     */
    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, null);
    }


    /**
     * inputstream 转字符串
     *
     * @param inputStream inputStream
     * @param charset     字符编码
     * @return string
     * @throws IOException IOException
     */
    public static String toString(InputStream inputStream, String charset) throws IOException {

        ByteArrayOutputStream result = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StringUtils.isEmpty(charset) ? UTF8_CHARSET : charset);
    }
}
