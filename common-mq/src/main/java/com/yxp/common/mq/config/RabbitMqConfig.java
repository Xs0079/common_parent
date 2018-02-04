package com.yxp.common.mq.config;

import com.yxp.common.mq.constant.ExchangeEnum;
import com.yxp.common.mq.constant.QueueEnum;
import com.yxp.common.mq.util.QueueAutoDeclareUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yxp.common.mq.producer.Publisher;

import java.util.Arrays;
import java.util.List;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

/**
 * RabbitMqConfig
 */
@Configuration
public class RabbitMqConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public List<Queue> queue() {
        return Arrays.asList(
                new Queue(QueueEnum.TEST_QUEUE.getQueueName()),
                new Queue(QueueEnum.YXP_NOTICE_QUEUE.getQueueName()),
                new Queue(QueueEnum.YXP_DELIVER_ORDER_QUEUE.getQueueName())
        );
    }

    @Bean
    public List<Exchange> exchange() {
        return Arrays.asList(
                ExchangeBuilder.directExchange(ExchangeEnum.TEST_EXCHANGE.getExchangeName()).build(),
                ExchangeBuilder.directExchange(ExchangeEnum.YXP_NOTICE_EXCHANGE.getExchangeName()).build(),
                ExchangeBuilder.directExchange(ExchangeEnum.YXP_DELIVER_ORDER_EXCHANGE.getExchangeName()).build()
        );
    }

    @Bean
    public List<Binding> binding() {
        return Arrays.asList(
                new Binding(
                        QueueEnum.TEST_QUEUE.getQueueName(),
                        QUEUE,
                        ExchangeEnum.TEST_EXCHANGE.getExchangeName(),
                        QueueEnum.TEST_QUEUE.getRoutingKey(),
                        null
                ),
                new Binding(
                        QueueEnum.YXP_NOTICE_QUEUE.getQueueName(),
                        QUEUE,
                        ExchangeEnum.YXP_NOTICE_EXCHANGE.getExchangeName(),
                        QueueEnum.YXP_NOTICE_QUEUE.getRoutingKey(),
                        null
                ),
                new Binding(
                        QueueEnum.YXP_DELIVER_ORDER_QUEUE.getQueueName(),
                        QUEUE,
                        ExchangeEnum.YXP_DELIVER_ORDER_EXCHANGE.getExchangeName(),
                        QueueEnum.YXP_DELIVER_ORDER_QUEUE.getRoutingKey(),
                        null
                )
        );
    }

    @Bean
    @ConditionalOnMissingBean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setCreateMessageIds(true);
        return messageConverter;
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public Publisher publisher(RabbitTemplate rabbitTemplate) {
        Publisher publisher = new Publisher(rabbitTemplate);
        return publisher;
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ConnectionFactory.class)
    public QueueAutoDeclareUtil queueAutoDeclareUtil() {
        QueueAutoDeclareUtil queueAutoDeclareUtil = new QueueAutoDeclareUtil(applicationContext);
        queueAutoDeclareUtil.declareRabbitConfig();
        return queueAutoDeclareUtil;
    }

}