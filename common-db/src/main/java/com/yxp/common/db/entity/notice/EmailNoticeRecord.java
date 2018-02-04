package com.yxp.common.db.entity.notice;

import com.yxp.common.db.entity.constant.IsSuccessEnum;

import java.util.Date;

/**
 * 邮件通知记录
 */
public class EmailNoticeRecord {
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

    /**
     * 邮件主题
     */
    private String subject;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    @Override
    public String toString() {
        return "EmailNoticeRecord{" +
                "id=" + id +
                ", addressee='" + addressee + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", isSuccess=" + isSuccess +
                ", subject='" + subject + '\'' +
                '}';
    }
}