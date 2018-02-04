package com.yxp.common.mq.msgEntity.notice;

/**
 * 消息通知对象
 * Created by Xs on 2018/1/12.
 */
public class NoticeMsg {

    /**
     * 发送终端 手机号/邮件地址 多个逗号分割
     */
    private String to;

    /**
     * 发送内容实体 or  微信消息中的msg3 通知内容
     */
    private String body;

    /**
     * 邮件主体 or 微信消息中的msg2 标题
     */
    private String subject;

    /**
     * 微信消息中的msg1 系统名称
     */
    private String sysName;

    /**
     * 消息来源
     */
    private String msgSource;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getMsgSource() {
        return msgSource;
    }

    public void setMsgSource(String msgSource) {
        this.msgSource = msgSource;
    }
}

