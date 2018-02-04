package com.yxp.common.mq.msgEntity.test;

/**
 * test
 * Created by Xs on 2018/1/2.
 */
public class TestEntity {

    private String name;

    private String desc;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
