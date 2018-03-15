package com.qeasy.samrtlockb.bean;


import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：添加人员
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/9
 * <p>
 * ==============================================
 */
public class Member implements Serializable {


   private  int  id;//会员关联锁id


    private  int  memberId;//会员id


    private  String  mobile;//手机号

    private  String  merchantId;//商户id

    private  String  smartlockId;//设备id


    private  String  serialNo;//设备编号

    private  int  isPinCode;// 是否数字密码 10 可用，20 禁止.


    private  String  pinCode;//数字密码


    private  int  isFingerprintCode;//是否指纹密码 10 可用，20 禁止.

    private  String  fingerprintCode;//指纹密码


    private  int  isIcCode;// 是否IC卡密码 10 可用，20 禁止.

    private  String  icCode;//IC卡密码

    private  int  status;// 状态 10 启用 20 停用 30 已过期.


    private  long  useStartTime;// 开始使用时间

    private  long  useEndTime;// 截止使用时间


    private  String  realName;//真实姓名


    private  String  identityCard;//身份证

    private  int  identityAuthentication;// 是否身份证授权 10 未认证 20 已认证.


    private  int  faceAuthentication;//是否人脸授权 10 未认证 20 已认证.

    private  long  authenticationTime;//授权时间

    private  int  frequency;//认证频度

    private  int  frequencyMode;//认证频度模式 10 天 20 周 30 月.

    private String frequencyModeTag ="";

    public String getFrequencyModeTag() {
        switch (getFrequencyMode()){
            case 10:
                frequencyModeTag= "天";
                break;
            case 20:
                frequencyModeTag= "周";
                break;
            case 30:
                frequencyModeTag= "月";
                break;
                default:
                    frequencyModeTag= "天";
        }
        return frequencyModeTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getSmartlockId() {
        return smartlockId;
    }

    public void setSmartlockId(String smartlockId) {
        this.smartlockId = smartlockId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getIsPinCode() {
        return isPinCode;
    }

    public void setIsPinCode(int isPinCode) {
        this.isPinCode = isPinCode;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getIsFingerprintCode() {
        return isFingerprintCode;
    }

    public void setIsFingerprintCode(int isFingerprintCode) {
        this.isFingerprintCode = isFingerprintCode;
    }

    public String getFingerprintCode() {
        return fingerprintCode;
    }

    public void setFingerprintCode(String fingerprintCode) {
        this.fingerprintCode = fingerprintCode;
    }

    public int getIsIcCode() {
        return isIcCode;
    }

    public void setIsIcCode(int isIcCode) {
        this.isIcCode = isIcCode;
    }

    public String getIcCode() {
        return icCode;
    }

    public void setIcCode(String icCode) {
        this.icCode = icCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(long useStartTime) {
        this.useStartTime = useStartTime;
    }

    public long getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(long useEndTime) {
        this.useEndTime = useEndTime;
    }

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

    public int getIdentityAuthentication() {
        return identityAuthentication;
    }

    public void setIdentityAuthentication(int identityAuthentication) {
        this.identityAuthentication = identityAuthentication;
    }

    public int getFaceAuthentication() {
        return faceAuthentication;
    }

    public void setFaceAuthentication(int faceAuthentication) {
        this.faceAuthentication = faceAuthentication;
    }


    public long getAuthenticationTime() {
        return authenticationTime;
    }

    public void setAuthenticationTime(long authenticationTime) {
        this.authenticationTime = authenticationTime;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequencyMode() {
        return frequencyMode;
    }

    public void setFrequencyMode(int frequencyMode) {
        this.frequencyMode = frequencyMode;
    }
}
