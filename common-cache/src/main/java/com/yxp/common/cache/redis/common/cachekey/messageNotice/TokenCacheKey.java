package com.yxp.common.cache.redis.common.cachekey.messageNotice;


import com.yxp.common.cache.redis.common.ICacheKey;

/**
 * 微信accessToken 缓存key
 */
public class TokenCacheKey implements ICacheKey {
    /**
     * 微信登录后token有效时间为7200秒，缓存保存: 2小时 -5分
     */
    private static final int EXPIRATIONTIME = (60 * 60 * 2) - (60 * 5);

    public static final TokenCacheKey instance = new TokenCacheKey();

    private final String prefix = "yxp:wechat:accessToken";


    /**
     * cache key对key统一管理
     *
     * @return Key
     */
    @Override
    public String getKey() {
        return prefix;
    }

    /**
     * cache timeout 绝对过期时间（秒），-1 代表永不过期
     *
     * @return ExpirationTime
     */
    @Override
    public int getExpirationTime() {
        return EXPIRATIONTIME;
    }

    /**
     * 本地缓存时间
     *
     * @return
     */
    @Override
    public int getLocalCacheTime() {
        return 36000;
    }
}
