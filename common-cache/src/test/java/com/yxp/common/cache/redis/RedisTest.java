package com.yxp.common.cache.redis;

import com.CommonCacheApplicationTests;
import com.yxp.common.cache.redis.common.ICacheKey;
import com.yxp.common.cache.redis.jedis.JedisTemplate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RedisTest
 * Created by Xs on 2018/1/10.
 */
public class RedisTest extends CommonCacheApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private JedisTemplate jedisTemplate;


    @Test
    public void testSave() {
        System.out.println("TestKey.instance.getKey() : " + TestKey.instance.getKey());
        jedisTemplate.put(TestKey.instance, "123");
        logger.info("TestKey.instance.getValue : {}", jedisTemplate.get(TestKey.instance));


        jedisTemplate.put(TestObjectKey.instance, new Person("张三", 500));
        Person person = jedisTemplate.get(TestObjectKey.instance, Person.class);
        logger.info("person name is  {} ; person age is {}", person.getName(), person.getAge());
    }
}

class TestKey implements ICacheKey {

    public static final TestKey instance = new TestKey();
    private final String prefix = "AllPackagePublishInfoKey";
    private final String separate = "-";
    private final String suffix = "all";
    private static final int expirationTime = 60;
    private static final int localCacheTime = 30;

    public int getExpirationTime() {
        return expirationTime;
    }

    public String getKey() {
        return prefix + separate + suffix;
    }

    public int getLocalCacheTime() {
        return localCacheTime;
    }
}


class TestObjectKey implements ICacheKey {

    public static final TestObjectKey instance = new TestObjectKey();
    private final String prefix = "testObj";
    private final String separate = "-";
    private final String suffix = "all";
    private static final int expirationTime = 60;
    private static final int localCacheTime = 30;

    public int getExpirationTime() {
        return expirationTime;
    }

    public String getKey() {
        return prefix + separate + suffix;
    }

    public int getLocalCacheTime() {
        return localCacheTime;
    }

}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}