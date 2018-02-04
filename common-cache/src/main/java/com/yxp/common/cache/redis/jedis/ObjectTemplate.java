package com.yxp.common.cache.redis.jedis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxp.common.cache.redis.common.ICacheKey;
import com.yxp.common.cache.redis.common.service.RedisObjectTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ObjectTemplate
 * Created by Xs on 2018/1/9.
 */
public class ObjectTemplate implements RedisObjectTemplate {
    private static final Logger logger = LoggerFactory.getLogger(StringTemplate.class);
    public static final String NULL = "null";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private StringTemplate stringTemplate;

    public ObjectTemplate(StringTemplate stringTemplate) {
        this.stringTemplate = stringTemplate;
    }

    @Override
    public void put(ICacheKey key, Object obj) {
        stringTemplate.put(key, objToJson(obj));
    }

    @Override
    public <T> T get(ICacheKey key, Class<T> clazz) {
        return jsonToObj(stringTemplate.get(key), clazz);
    }


    @Override
    public <T> void lpush(ICacheKey key, T... t) {
        Arrays.stream(t).forEach(element -> stringTemplate.lpush(key, objToJson(element)));
    }

    @Override
    public <T> void rpush(ICacheKey key, T... t) {
        Arrays.stream(t).forEach(element -> stringTemplate.rpush(key, objToJson(element)));
    }

    @Override
    public <T> T lPop(ICacheKey key, Class<T> clazz) {
        return jsonToObj(stringTemplate.lPop(key), clazz);
    }

    @Override
    public <T> T rPop(ICacheKey key, Class<T> clazz) {
        return jsonToObj(stringTemplate.rPop(key), clazz);
    }

    @Override
    public <T> T lGet(ICacheKey key, int index, Class<T> clazz) {
        return jsonToObj(stringTemplate.lGet(key, index), clazz);
    }

    @Override
    public <T> List<T> lGetAll(ICacheKey key, Class<T> clazz) {
        List<String> jsonList = stringTemplate.lGetAll(key);
        if (jsonList.size() > 1000) {
            logger.error("redis ObjectTemplate 获取list对象值大于1000 ， key : {} ; classType : {}", key.getKey(), clazz);
            logger.error("redis ObjectTemplate 获取list对象值大于1000 ， key : {} ; classType : {}", key.getKey(), clazz);
            logger.error("redis ObjectTemplate 获取list对象值大于1000 ， key : {} ; classType : {}", key.getKey(), clazz);
        }
        List<T> list = new ArrayList<>(jsonList.size());
        jsonList.forEach(json -> list.add(jsonToObj(json, clazz)));
        return list;
    }

    /**
     * 对象 转 json
     *
     * @param obj
     * @return
     */
    private String objToJson(Object obj) {
        if (!(ObjectUtils.isEmpty(obj))) {
            try {
                return MAPPER.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                logger.error("对象转json异常 ： " + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

        return "";
    }

    /**
     * json 转 对象
     *
     * @param json
     * @return
     */

    private <T> T jsonToObj(String json, Class<T> clazz) {
        if (!(StringUtils.isEmpty(json) || json.toLowerCase().equals(NULL))) {
            try {
                return MAPPER.readValue(json, clazz);
            } catch (IOException e) {
                logger.error("对象转json异常 ： " + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
