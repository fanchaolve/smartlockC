package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.HomeContract;
import com.qeasy.samrtlockb.base.v.SettingContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class SettingModle implements SettingContract.Model {


    private ApiService service;



    public SettingModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }

    @Override
    public void merchant_smartlock_changeInfo(String serialNo, int frequency, int frequencyMode, Callback callback) {
        Call call =service.merchant_smartlock_changeInfo(serialNo,frequency,frequencyMode);
        call.enqueue(callback);
    }

    @Override
    public void merchant_smartlock_unBinding(String serialNo, Callback callback) {
        Call call =service.merchant_smartlock_unBinding(serialNo);
        call.enqueue(callback);
    }

    @Override
    public void smartlock_stop(String serialNo, Callback callback) {
        Call call =service.smartlock_stop(serialNo);
        call.enqueue(callback);
    }



}
