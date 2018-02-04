package com.yxp.common.cache.redis.common.cachekey.messageHandler;

import com.yxp.common.cache.redis.common.ICacheKey;

/**
 * 失败的消息 Key
 * Created by Xs on 2018/1/12.
 */
public class FailedMsgKey implements ICacheKey {

    private static final int EXPIRATIONTIME = 60 * 5;
    private final String prefix = "yxp:msg:failed";
    private String msgId;

    public FailedMsgKey(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public int getExpirationTime() {
        return EXPIRATIONTIME;
    }

    @Override
    public String getKey() {
        return prefix + SEPARATE + msgId;
    }

    @Override
    public int getLocalCacheTime() {
        return EXPIRATIONTIME;
    }

}
