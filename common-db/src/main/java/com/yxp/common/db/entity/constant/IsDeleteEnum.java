package com.yxp.common.db.entity.constant;

import com.yxp.common.db.plugin.mybatis.typehandler.BaseCodeEnum;

/**
 * IsDeleteEnum
 * Created by Xs on 2018/1/19.
 */
public enum IsDeleteEnum implements BaseCodeEnum {
    DELETED((byte) 1, "已删除"),
    NOT_DELETE((byte) 0, "未删除");

    private byte code;
    private String desc;

    IsDeleteEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
