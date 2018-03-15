package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.SettingContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class TempPasswordModle implements TemporarypassContract.Model {


    private ApiService service;



    public TempPasswordModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }

    @Override
    public void changeInfo(String serialNo, String pinCode, String fingerprintCode, String icCode, Callback callback) {
        Call call =service.merchant_smartlock_changeInfo(serialNo,pinCode,fingerprintCode,icCode);
        call.enqueue(callback);
    }
}
