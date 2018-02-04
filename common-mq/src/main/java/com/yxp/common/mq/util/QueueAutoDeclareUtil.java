package com.yxp.common.mq.util;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * 自动声明定义queue
 * Created by Xs on 2018/1/22.
 */
public class QueueAutoDeclareUtil {
    private volatile ApplicationContext applicationContext;

    public QueueAutoDeclareUtil(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public void declareRabbitConfig() {
        List<Queue> queueList = (List<Queue>) this.applicationContext.getBean("queue");
        List<Exchange> exchangeList = (List<Exchange>) this.applicationContext.getBean("exchange");
        List<Binding> bindingList = (List<Binding>) this.applicationContext.getBean("binding");

        RabbitTemplate rabbittemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbittemplate.execute(new ChannelCallback<Object>() {

            @Override
            public Object doInRabbit(Channel channel) throws Exception {
                try {
                    for (int i = 0; i < queueList.size(); ++i) {
                        Queue queue = queueList.get(i);
                        if (queue.getName().startsWith("amq.")) {
                            continue;
                        }
                        channel.queueDeclare(queue.getName(),
                                queue.isDurable(),
                                queue.isExclusive(),
                                queue.isAutoDelete(),
                                queue.getArguments());
                    }


                    for (int i = 0; i < exchangeList.size(); ++i) {
                        Exchange exchange = exchangeList.get(i);
                        channel.exchangeDeclare(exchange.getName(),
                                exchange.getType(),
                                exchange.isDurable(),
                                exchange.isAutoDelete(),
                                exchange.isInternal(),
                                exchange.getArguments());
                    }


                    for (int i = 0; i < bindingList.size(); ++i) {
                        Binding binding = bindingList.get(i);
                        channel.queueBind(binding.getDestination(),
                                binding.getExchange(),
                                binding.getRoutingKey(),
                                binding.getArguments());

                    }

                    return null;
                } catch (IOException e) {
                    channel.close();
                    throw new IOException(e);
                }
            }
        });

    }
}
