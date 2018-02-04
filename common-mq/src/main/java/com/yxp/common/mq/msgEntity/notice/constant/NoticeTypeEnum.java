package com.yxp.common.mq.msgEntity.notice.constant;

/**
 * 消息通知枚举
 * Created by Xs on 2018/1/12.
 */
public enum NoticeTypeEnum {
    SEND_MAIL("send_mail", "邮件"),
    SEND_WECHAT("send_wechat", "微信"),
    SEND_SMS("send_sms", "短信");

    /**
     * 消息类型
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    NoticeTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
