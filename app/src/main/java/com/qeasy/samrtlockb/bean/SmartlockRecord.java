package com.qeasy.samrtlockb.bean;


import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/7
 * <p>
 * ==============================================
 */
public class SmartlockRecord implements Serializable{


    /**
     * 姓名
     identityCard
     true string
     身份证
     mobile
     true string
     手机号
     serialNo
     true string
     锁编号
     unlockTime
     true number
     开锁时间
     unlockMode
     true number
     开锁模式 10 密码 20 指纹 30 IC卡
     */
    private String realName ="";

    private String identityCard ="";

    private String mobile ="";

    private String serialNo ="";

    private long unlockTime ;

    private String unlockMode ="";

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public long getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(long unlockTime) {
        this.unlockTime = unlockTime;
    }

    public String getUnlockMode() {
        return unlockMode;
    }

    public void setUnlockMode(String unlockMode) {
        this.unlockMode = unlockMode;
    }
}
