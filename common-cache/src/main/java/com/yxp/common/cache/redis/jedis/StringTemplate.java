package com.yxp.common.cache.redis.jedis;

import com.yxp.common.cache.redis.common.ICacheKey;
import com.yxp.common.cache.redis.common.service.RedisStringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * StringTemplate
 * Created by Xs on 2018/1/9.
 */
public class StringTemplate implements RedisStringTemplate {

    private static final Logger logger = LoggerFactory.getLogger(StringTemplate.class);

    private StringRedisTemplate stringTemplate;

    public StringTemplate(StringRedisTemplate stringTemplate) {
        this.stringTemplate = stringTemplate;
    }

    @Override
    public void del(ICacheKey key) {
        stringTemplate.delete(key.getKey());
    }

    @Override
    public boolean expire(ICacheKey key) {
        return stringTemplate.expire(key.getKey(), key.getExpirationTime(), TimeUnit.SECONDS);
    }

    @Override
    public void put(ICacheKey key, String value) {
        Assert.notNull(value, "参数value不能为null....");
        stringTemplate.opsForValue().set(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public String get(ICacheKey key) {
        return stringTemplate.opsForValue().get(key.getKey());
    }

    @Override
    public boolean exists(ICacheKey key) {
        return stringTemplate.hasKey(key.getKey());
    }

    @Override
    public void lpush(ICacheKey key, String... value) {
        stringTemplate.opsForList().leftPushAll(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public void rpush(ICacheKey key, String... value) {
        stringTemplate.opsForList().rightPushAll(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public String lPop(ICacheKey key) {
        return stringTemplate.opsForList().leftPop(key.getKey());
    }

    @Override
    public String rPop(ICacheKey key) {
        return stringTemplate.opsForList().rightPop(key.getKey());
    }

    @Override
    public List<String> lGetAll(ICacheKey key) {
        return stringTemplate.opsForList().range(key.getKey(), 0, -1);
    }

    @Override
    public List<String> lrange(ICacheKey key, int fromIndex, int lastIndex) {
        return stringTemplate.opsForList().range(key.getKey(), fromIndex, lastIndex);
    }

    @Override
    public String lGet(ICacheKey key, int index) {
        return stringTemplate.opsForList().index(key.getKey(), index);
    }

    @Override
    public long lLen(ICacheKey key) {
        return stringTemplate.opsForList().size(key.getKey());
    }

    @Override
    public long sAdd(ICacheKey key, String... members) {
        long n = stringTemplate.opsForSet().add(key.getKey(), members);
        setExpireTime(key);
        return n;
    }

    @Override
    public Set<String> sMembers(ICacheKey key) {
        return stringTemplate.opsForSet().members(key.getKey());
    }

    @Override
    public Long sRem(ICacheKey key, String... members) {
        return stringTemplate.opsForSet().remove(key.getKey(), members);
    }

    @Override
    public Long sLen(ICacheKey key) {
        return stringTemplate.opsForSet().size(key.getKey());
    }

    @Override
    public Boolean sIsMember(ICacheKey key, String member) {
        return stringTemplate.opsForSet().isMember(key.getKey(), member);
    }

    @Override
    public long incr(ICacheKey key) {
        Long increment = stringTemplate.opsForValue().increment(key.getKey(), 1);
        setExpireTime(key);
        return increment;
    }

    @Override
    public long decr(ICacheKey key) {
        Long decrement = stringTemplate.opsForValue().increment(key.getKey(), -1);
        setExpireTime(key);
        return decrement;
    }

    @Override
    public boolean setnx(String key, String expireTime) {
        return stringTemplate.opsForValue().setIfAbsent(key, expireTime);
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     */
    private void setExpireTime(ICacheKey key) {
        if (key.getExpirationTime() != -1) {
            stringTemplate.expire(key.getKey(), key.getExpirationTime(), TimeUnit.SECONDS);
        }
    }
}
