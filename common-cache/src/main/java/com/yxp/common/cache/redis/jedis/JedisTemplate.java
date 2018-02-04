package com.yxp.common.cache.redis.jedis;

import com.yxp.common.cache.redis.common.ICacheKey;
import com.yxp.common.cache.redis.common.service.RedisObjectTemplate;
import com.yxp.common.cache.redis.common.service.RedisStringTemplate;

import java.util.List;
import java.util.Set;

/**
 * 统一对外的redisTemplate
 * l开头的方法对应操作list的方法 99%
 * 开头的方法对应操作set的方法 99%s
 * Created by Xs on 2018/1/9.
 */
public class JedisTemplate implements RedisStringTemplate, RedisObjectTemplate {

    private StringTemplate stringTemplate;
    private ObjectTemplate objectTemplate;

    public JedisTemplate(StringTemplate stringTemplate, ObjectTemplate objectTemplate) {
        this.stringTemplate = stringTemplate;
        this.objectTemplate = objectTemplate;
    }

    @Override
    public void del(ICacheKey key) {
        stringTemplate.del(key);
    }

    @Override
    public boolean expire(ICacheKey key) {
        return stringTemplate.expire(key);
    }

    @Override
    public void put(ICacheKey key, String value) {
        stringTemplate.put(key, value);
    }

    @Override
    public String get(ICacheKey key) {
        return stringTemplate.get(key);
    }

    @Override
    public boolean exists(ICacheKey key) {
        return stringTemplate.exists(key);
    }

    @Override
    public void lpush(ICacheKey key, String... value) {
        stringTemplate.lpush(key, value);
    }

    @Override
    public void rpush(ICacheKey key, String... value) {
        stringTemplate.rpush(key, value);
    }

    @Override
    public String lPop(ICacheKey key) {
        return stringTemplate.lPop(key);
    }

    @Override
    public String rPop(ICacheKey key) {
        return stringTemplate.rPop(key);
    }

    @Override
    public List<String> lGetAll(ICacheKey key) {
        return stringTemplate.lGetAll(key);
    }

    @Override
    public List<String> lrange(ICacheKey key, int fromIndex, int lastIndex) {
        return stringTemplate.lrange(key, fromIndex, lastIndex);
    }

    @Override
    public String lGet(ICacheKey key, int index) {
        return stringTemplate.lGet(key, index);
    }

    @Override
    public long lLen(ICacheKey key) {
        return stringTemplate.lLen(key);
    }

    @Override
    public long sAdd(ICacheKey key, String... members) {
        return stringTemplate.sAdd(key, members);
    }

    @Override
    public Set<String> sMembers(ICacheKey key) {
        return stringTemplate.sMembers(key);
    }

    @Override
    public Long sRem(ICacheKey key, String... members) {
        return stringTemplate.sRem(key, members);
    }

    @Override
    public Long sLen(ICacheKey key) {
        return stringTemplate.sLen(key);
    }

    @Override
    public Boolean sIsMember(ICacheKey key, String member) {
        return stringTemplate.sIsMember(key, member);
    }

    @Override
    public long incr(ICacheKey key) {
        return stringTemplate.incr(key);
    }

    @Override
    public long decr(ICacheKey key) {
        return stringTemplate.decr(key);
    }

    @Override
    public boolean setnx(String key, String expireTime) {
        return stringTemplate.setnx(key, expireTime);
    }

    @Override
    public void put(ICacheKey key, Object obj) {
        objectTemplate.put(key, obj);
    }

    @Override
    public <T> T get(ICacheKey key, Class<T> clazz) {
        return objectTemplate.get(key, clazz);
    }

    @Override
    public <T> void lpush(ICacheKey key, T[] t) {
        objectTemplate.lpush(key, t);
    }

    @Override
    public <T> void rpush(ICacheKey key, T[] t) {
        objectTemplate.rpush(key, t);
    }

    @Override
    public <T> T lPop(ICacheKey key, Class<T> clazz) {
        return objectTemplate.lPop(key, clazz);
    }

    @Override
    public <T> T rPop(ICacheKey key, Class<T> clazz) {
        return objectTemplate.rPop(key, clazz);
    }

    @Override
    public <T> List<T> lGetAll(ICacheKey key, Class<T> clazz) {
        return objectTemplate.lGetAll(key, clazz);
    }

    @Override
    public <T> T lGet(ICacheKey key, int index, Class<T> clazz) {
        return objectTemplate.lGet(key, index, clazz);
    }
}
