package com.youxinpai.common.util.web.http;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * HeaderUtil
 * Created by Xs on 2018/1/17.
 */
public class HeaderUtil {
    /**
     * json头
     */
    public static final Header JSON_HEADER = new BasicHeader("Content-type", "application/json");

    /**
     * 下载及上传头
     */
    public static final Header OCTET_HEADER = new BasicHeader("Content-type", "application/octet-stream;charset=utf-8");

    /**
     * 下载时描述头指定文件名名称，名称自定义
     */
    public static final Header CONTENT_DISPOSITION_HEADER = new BasicHeader("Content-Disposition", "attachment; filename=");


}
