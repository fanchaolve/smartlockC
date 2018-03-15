package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2016/12/5.
 */

public class LoginModel implements LoginContract.Model {

    private ApiService service;


    public LoginModel() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void getTelCode(String phone, Callback callback) {
        Call call =service.sms_sendLoginSmsCode(phone);
        call.enqueue(callback);
    }

    @Override
    public void login(String phone, String code, Callback callback) {
        Call call =service.merchant_login(phone,code);
        call.enqueue(callback);
    }
}
