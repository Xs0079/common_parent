package com.yxp.common.cache.redis.common.cachekey.messageNotice;


import com.yxp.common.cache.redis.common.ICacheKey;

/**
 * x
 */
public class LimitMsgSignCacheKey implements ICacheKey {
    private final String prefix = "yxp:notice:limit";
    private final String suffix = "msgId";
    // 5分钟
    private static final int EXPIRATIONTIME = 60 * 5;
    private String msgId;

    public LimitMsgSignCacheKey(String msgId) {
        this.msgId = msgId;
    }

    /**
     * cache key对key统一管理
     *
     * @return Key
     */
    @Override
    public String getKey() {
        return prefix + SEPARATE + suffix + SEPARATE + msgId;
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
