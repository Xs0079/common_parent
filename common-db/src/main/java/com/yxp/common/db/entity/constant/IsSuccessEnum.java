package com.yxp.common.db.entity.constant;

import com.yxp.common.db.plugin.mybatis.typehandler.BaseCodeEnum;

/**
 * IsSuccessEnum
 * Created by Xs on 2018/1/19.
 */
public enum IsSuccessEnum implements BaseCodeEnum {
    SUCCESS((byte) 1, "成功"),
    FAILED((byte) 0, "失败");

    /**
     * 数据库存放的code
     */
    private byte code;

    /**
     * 描述
     */
    private String desc;

    IsSuccessEnum(byte code, String desc) {
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
