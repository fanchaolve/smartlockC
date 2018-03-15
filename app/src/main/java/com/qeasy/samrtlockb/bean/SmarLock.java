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
 * 时间：2018/3/6
 * <p>
 * ==============================================
 */
public class SmarLock implements Serializable {


    /**
     * fingerprintCode : 2321
     * useStartTime : 1508501400000
     * icCode : 3212321
     * serialNo : SHP0022134
     * faceAuthentication : 20
     * useEndTime : 1540037400000
     * overdue : false
     * merchantId : 1
     * pinCode : 567820
     * id : 4
     * isFingerprintCode : 10
     * isIcCode : 10
     * shortName : 智能便捷锁智
     * identityAuthentication : 20
     * isPinCode : 10
     */

    private String fingerprintCode = "";
    private long useStartTime;
    private String icCode = "";
    private String serialNo = "";
    private int faceAuthentication;
    private long useEndTime;
    private boolean overdue;
    private int merchantId;
    private String pinCode ="";
    private int id;
    private int isFingerprintCode;
    private int isIcCode;
    private String shortName ="";
    private int identityAuthentication;
    private int isPinCode;

    public String getFingerprintCode() {
        return fingerprintCode;
    }

    public void setFingerprintCode(String fingerprintCode) {
        this.fingerprintCode = fingerprintCode;
    }

    public long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(long useStartTime) {
        this.useStartTime = useStartTime;
    }

    public String getIcCode() {
        return icCode;
    }

    public void setIcCode(String icCode) {
        this.icCode = icCode;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getFaceAuthentication() {
        return faceAuthentication;
    }

    public void setFaceAuthentication(int faceAuthentication) {
        this.faceAuthentication = faceAuthentication;
    }

    public long getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(long useEndTime) {
        this.useEndTime = useEndTime;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsFingerprintCode() {
        return isFingerprintCode;
    }

    public void setIsFingerprintCode(int isFingerprintCode) {
        this.isFingerprintCode = isFingerprintCode;
    }

    public int getIsIcCode() {
        return isIcCode;
    }

    public void setIsIcCode(int isIcCode) {
        this.isIcCode = isIcCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getIdentityAuthentication() {
        return identityAuthentication;
    }

    public void setIdentityAuthentication(int identityAuthentication) {
        this.identityAuthentication = identityAuthentication;
    }

    public int getIsPinCode() {
        return isPinCode;
    }

    public void setIsPinCode(int isPinCode) {
        this.isPinCode = isPinCode;
    }
}
