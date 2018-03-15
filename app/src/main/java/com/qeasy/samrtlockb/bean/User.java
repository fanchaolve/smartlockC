package com.qeasy.samrtlockb.bean;

/**
 * Created by Administrator on 2017/1/6.
 */

public class User {


    /**
     *  true string
     返回的token值，后续需要加入验证
     merchantId
     true number
     商户ID号
     mobile
     true string
     手机号
     expiresIn
     true number
     过期时间

     */


    private int id;

    private String accessToken="";
    private String memberId="";
    private String mobile ="";
    private String expiresIn ="";

    private String serialNo="";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
}
