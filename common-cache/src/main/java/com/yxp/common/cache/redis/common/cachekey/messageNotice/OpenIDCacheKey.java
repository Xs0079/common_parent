package com.yxp.common.cache.redis.common.cachekey.messageNotice;


import com.yxp.common.cache.redis.common.ICacheKey;

/**
 * 微信openId Key
 */
public class OpenIDCacheKey implements ICacheKey {
    private final String prefix = "yxp:wechat:openId";
    private final String suffix = "userName";
    // 一天
    private static final int EXPIRATIONTIME = 3600 * 24;
    private String userName;

    public OpenIDCacheKey(String userName) {
        this.userName = userName;
    }

    /**
     * cache key对key统一管理
     *
     * @return Key
     */
    @Override
    public String getKey() {
        return prefix + SEPARATE + suffix + SEPARATE + userName;
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
