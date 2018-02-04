package com.yxp.common.mq.constant;

/**
 * 所有的Queue定义枚举类
 * Created by Xs on 2017/12/29.
 */
public enum QueueEnum {

    TEST_QUEUE(
            "test-queue",
            "生产者：local",
            "消费者：local",
            "test",
            "测试用"
    ),
    YXP_DELIVER_ORDER_QUEUE(
            "q_yxp_deliver_order",
            "",//TODO：生产者
            "",//TODO：消费者
            "yxp_deliver_order",
            ""//TODO： 队列描述
    ),
    YXP_NOTICE_QUEUE(
            "yxp_notice_queue",
            "生产者： php端 其他系统 邵元会处",
            "消费者： 本组message-handler项目消费",
            "yxp_notice_rk",
            "存储PHP端发送的消息通知实体"
    );

    /**
     * queueName名称
     */
    private String queueName;
    /**
     * 生产者项目名称，多个逗号分割
     */
    private String producerProject;
    /**
     * 消费者项目名称，多个逗号分割
     */
    private String consumerProject;

    /***
     * exchange到队列的routingKey
     */
    private String routingKey;

    /**
     * 业务场景描述 及 说明信息 越详细越好
     */
    private String desc;

    QueueEnum(String queueName, String producerProject, String consumerProject, String routingKey, String desc) {
        this.queueName = queueName;
        this.producerProject = producerProject;
        this.consumerProject = consumerProject;
        this.routingKey = routingKey;
        this.desc = desc;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getProducerProject() {
        return producerProject;
    }

    public void setProducerProject(String producerProject) {
        this.producerProject = producerProject;
    }

    public String getConsumerProject() {
        return consumerProject;
    }

    public void setConsumerProject(String consumerProject) {
        this.consumerProject = consumerProject;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
