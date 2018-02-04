package com.yxp.common.mq.msgEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.youxinpai.common.util.utils.JsonUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息基础父类，对应消息失败数据库
 * Created by Xs on 2018/1/2.
 */
public class BaseMessageEntity implements Serializable {
    private Long id;
    private String messageNo;
    private String exchange;
    private String routeKey;
    private String messageType;
    private String messageBody;
    private Integer flagDeleted;
    private Integer failCount;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setMessageBody(Object obj) throws JsonProcessingException {
        this.messageBody = JsonUtil.toJson(obj);
    }

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Integer flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String toJson() throws JsonProcessingException {
        return JsonUtil.toJson(this);
    }
}
