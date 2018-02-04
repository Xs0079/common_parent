package com.yxp.common.mq;

import com.yxp.common.mq.producer.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonMqApplicationTests {

    @Autowired
    private Publisher publisher;

    @Test
    public void testSend() {
//        for (int x = 0; x < 100; x++) {
//            TestEntity testEntity = new TestEntity();
//            testEntity.setName("测试 " + x);
//            testEntity.setDesc("123");
//
//            publisher.sendMessage(ExchangeEnum.TEST_EXCHANGE.getExchangeName(), "test", testEntity);
//            publisher.sendMessage(ExchangeEnum.TEST_EXCHANGE.getExchangeName(), "test", testEntity);
//            publisher.sendMessage(ExchangeEnum.TEST_EXCHANGE.getExchangeName(), "test", testEntity);
//            publisher.sendMessage(ExchangeEnum.TEST_EXCHANGE.getExchangeName(), "123", testEntity);
//            publisher.sendMessage(ExchangeEnum.TEST_EXCHANGE.getExchangeName(), "111", testEntity);
//        }
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
