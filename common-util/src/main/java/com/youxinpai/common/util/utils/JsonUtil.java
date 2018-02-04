package com.youxinpai.common.util.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json util
 * Created by Xs on 2018/1/19.
 */
public class JsonUtil {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final String NULL_STR = "";


    static {
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return "";
        }

        return jsonMapper.writeValueAsString(obj);
    }

    /**
     * json转对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T toObj(String json, Class<T> clazz) throws IOException {
        return jsonMapper.readValue(json, clazz);
    }

    /**
     * 对象转json， 异常返回空字符串
     *
     * @param obj
     * @return
     */
    public static String toJsonUncheck(Object obj) {
        try {
            return toJson(obj);
        } catch (JsonProcessingException e) {
            return NULL_STR;
        }
    }

    /**
     * json转对象 异常返回null
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T toObjUncheck(String json, Class<T> clazz) {
        try {
            return toObj(json, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
