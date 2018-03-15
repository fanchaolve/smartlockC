package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.AddressContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AddressModle implements AddressContract.Model {


    private ApiService service;



    public AddressModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }



    @Override
    public void changeInfo(String serialNo, String address, Callback callback) {
        Call call =service.merchant_smartlock_changeInfo(serialNo,address);
        call.enqueue(callback);
    }
}
