package com.yxp.common.mq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.youxinpai.common.util.utils.JsonUtil;
import com.yxp.common.mq.constant.ExchangeEnum;
import com.yxp.common.mq.constant.QueueEnum;
import com.yxp.common.mq.msgEntity.BaseMessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * 定义统一的消息处理
 * Created by Xs on 2017/12/29.
 */
public class Publisher {
    private Logger logger = LoggerFactory.getLogger(Publisher.class);

    private RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送消息
     *
     * @param exchangeEnum common-mq中定义的 exchangeEnum
     * @param queueEnum    common-mq中定义的 QueueEnum
     * @param payload      发送实体
     */
    public void sendMessage(ExchangeEnum exchangeEnum, QueueEnum queueEnum, Object payload) {
        validateMessage(exchangeEnum.getExchangeName(), queueEnum.getQueueName());
        BaseMessageEntity msg = createMsg(exchangeEnum, queueEnum, payload);
        rabbitTemplate.convertAndSend(exchangeEnum.getExchangeName(), queueEnum.getRoutingKey(), msg);
    }


    /**
     * 发送消息
     * baseMessageEntity的exchange与routingKey不能为空
     *
     * @param baseMessageEntity baseMessageEntity
     */
    public void sendMessage(BaseMessageEntity baseMessageEntity) {
        validateMessage(baseMessageEntity.getExchange(), baseMessageEntity.getRouteKey());
        rabbitTemplate.convertAndSend(baseMessageEntity.getExchange(), baseMessageEntity.getRouteKey(), baseMessageEntity);
        logger.info(String.format("发送消息 ==> {}", baseMessageEntity.toString()));
    }


    /**
     * 对消息进行验证
     *
     * @param exchangeName exchangeName
     * @param routingKey   routingKey
     */
    private void validateMessage(String exchangeName, String routingKey) {
        if (StringUtils.isEmpty(exchangeName)) {
            throw new RuntimeException("发送消息的exchange不能为空");
        }

        if (StringUtils.isEmpty(routingKey)) {
            throw new RuntimeException("发送消息的routeKey不能为空");
        }
    }

    /**
     * 创建消息体
     *
     * @param exchangeEnum
     * @param queueEnum
     * @param payload
     * @return
     */
    public static BaseMessageEntity createMsg(ExchangeEnum exchangeEnum, QueueEnum queueEnum, Object payload) {
        BaseMessageEntity baseMessageEntity = new BaseMessageEntity();
        baseMessageEntity.setRouteKey(queueEnum.getRoutingKey());
        baseMessageEntity.setExchange(exchangeEnum.getExchangeName());
        baseMessageEntity.setCreateTime(new Date());

        if (payload instanceof String) {
            baseMessageEntity.setMessageBody((String) payload);
        } else {
            try {
                baseMessageEntity.setMessageBody(JsonUtil.toJson(payload));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("发送消息时 消息内容转json失败 : " + e.getMessage(), e);
            }
        }
        return baseMessageEntity;
    }
}
