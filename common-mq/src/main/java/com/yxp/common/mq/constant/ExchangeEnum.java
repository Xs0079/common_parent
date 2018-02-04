package com.yxp.common.mq.constant;


import org.springframework.amqp.core.ExchangeTypes;

/**
 * 所有的exchange枚举类
 * Created by Xs on 2017/12/29.
 */
public enum ExchangeEnum {
    TEST_EXCHANGE(
            "testExchange",
            ExchangeTypes.DIRECT,
            "test,,",
            "test 用"
    ), YXP_DELIVER_ORDER_EXCHANGE(
            "ex_yxp_deliver_order",
            ExchangeTypes.DIRECT,
            "q_yxp_deliver_order",
            ""//TODO:业务逻辑说明需补充
    ),
    YXP_NOTICE_EXCHANGE(
            "yxp_notice_ex",
            ExchangeTypes.DIRECT,
            "yxp_notice_queue",
            "PHP-邵元会-处调用发送消息的exchange，根据对方发送的不同消息类型发送短信/邮件/微信通知等。"
    );

    /**
     * rabbit exchange名称
     */
    private String exchangeName;
    /**
     * rabbit exchange类型：ExchangeTypes枚举的一种
     */
    private String exchangeType;
    /**
     * 关联的Queue 集合，多个用逗号分割 routingKey : queue,routingKey : queue,
     */
    private String queueList;
    /**
     * 描述
     */
    private String desc;

    ExchangeEnum(String exchangeName, String exchangeType, String queueList, String desc) {
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
        this.queueList = queueList;
        this.desc = desc;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getQueueList() {
        return queueList;
    }

    public void setQueueList(String queueList) {
        this.queueList = queueList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}