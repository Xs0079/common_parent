package com.yxp.common.cache.redis.common;

/**
 * cache key 通过实例化cache key对key统一管理
 *
 * @author wangjiaxi
 */
public interface ICacheKey {

    String SEPARATE = ":";

    /**
     * cache key对key统一管理
     *
     * @return Key
     */
    String getKey();

    /**
     * cache timeout 绝对过期时间（秒），-1 代表永不过期
     *
     * @return ExpirationTime
     */
    int getExpirationTime();

    /**
     * 本地缓存时间
     *
     * @return
     */
    int getLocalCacheTime();
}
