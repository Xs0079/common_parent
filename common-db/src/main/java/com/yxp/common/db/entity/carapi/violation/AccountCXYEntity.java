package com.yxp.common.db.entity.carapi.violation;

/**
 * 车行易账号实体
 * @author yanzongrui
 */
public class AccountCXYEntity {
    /**
     * 系统Id
     */
    private Integer sysId;
    /**
     * 账户名称
     */
    private String userName;
    /**
     * 账户密码
     */
    private String password;

    /**
     *验签
     */
    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
