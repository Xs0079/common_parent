package com.yxp.common.db.entity.notice;

import com.yxp.common.db.entity.constant.IsSuccessEnum;

import java.util.Date;

/**
 * 短信通知实体
 */
public class SmsNoticeRecord {
    
    private Long id;

    /**
     * 收信人
     */
    private String addressee;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送时间爱你
     */
    private Date sendTime;

    /**
     * 是否成功
     */
    private IsSuccessEnum isSuccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee == null ? null : addressee.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public IsSuccessEnum getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(IsSuccessEnum isSuccess) {
        this.isSuccess = isSuccess;
    }

}