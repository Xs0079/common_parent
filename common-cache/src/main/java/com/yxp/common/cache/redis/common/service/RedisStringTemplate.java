package com.yxp.common.cache.redis.common.service;

import com.yxp.common.cache.redis.common.ICacheKey;

import java.util.List;
import java.util.Set;

/**
 * Redis操作统一接口，只支持String类型的key/value
 * <p>
 * l开头的方法为操作list (不绝对 99%)
 * s开头的方法为操作set (不绝对 99%)
 * <p>
 * Created by Xs on 2018/1/9.
 */
public interface RedisStringTemplate {

    /**
     * redis移除key
     *
     * @param key
     */
    void del(ICacheKey key);

    /**
     * 为指定key设置生存时间
     *
     * @param key
     * @return
     */
    boolean expire(ICacheKey key);

    /**
     * 添加key value
     *
     * @param key   String
     * @param value String
     */
    void put(ICacheKey key, String value);

    /**
     * 获取指定key的value
     *
     * @param key
     * @return
     */
    String get(ICacheKey key);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean exists(ICacheKey key);


    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key   key
     * @param value value
     */
    void lpush(ICacheKey key, String... value);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾
     *
     * @param key   key
     * @param value value
     */
    void rpush(ICacheKey key, String... value);

    /**
     * 移除并返回列key的头元素
     *
     * @param key
     * @return
     */
    String lPop(ICacheKey key);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * @param key
     * @return
     */
    String rPop(ICacheKey key);


    /**
     * 取出key指定的list中所有的元素
     *
     * @param key key
     * @return
     */
    List<String> lGetAll(ICacheKey key);

    /**
     * 取出key指定的list中 包含fromIndex - lastIndex 之间的所有元素，包含fromIndex、lastIndex
     *
     * @param key
     * @param fromIndex 起始范围(包含)
     * @param lastIndex 接触范围(包含)
     * @return
     */
    List<String> lrange(ICacheKey key, int fromIndex, int lastIndex);

    /**
     * 取出list中下标标识的元素
     *
     * @param key   key
     * @param index 下标
     * @return
     */
    String lGet(ICacheKey key, int index);

    /**
     * 获取指定list的长度
     *
     * @param key key
     * @return
     */
    long lLen(ICacheKey key);

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     *
     * @param key
     * @param members
     * @return
     */
    long sAdd(ICacheKey key, final String... members);

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     *
     * @param key
     * @return
     */
    Set<String> sMembers(ICacheKey key);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key
     * @param members
     * @return
     */
    Long sRem(ICacheKey key, String... members);

    /**
     * 返回集合 key 集合中元素的数量。当 key 不存在时，返回 0 。
     *
     * @param key
     * @return
     */
    Long sLen(ICacheKey key);

    /**
     * 判断 member 元素是否集合 key 的成员。
     *
     * @param key
     * @param member
     * @return
     */
    Boolean sIsMember(ICacheKey key, String member);

    /**
     * 将 key 中储存的数字值增一
     *
     * @param key
     * @return
     */
    long incr(ICacheKey key);

    /**
     * 将 key 中储存的数字值减一
     *
     * @param key
     * @return
     */
    public long decr(ICacheKey key);


    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     *
     * @param key
     * @param expireTime
     * @return 设置成功，返回 true || 设置失败，返回 false 。
     */
    public boolean setnx(String key, String expireTime);
}
