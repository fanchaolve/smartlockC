package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.AddmemberContract;
import com.qeasy.samrtlockb.base.v.UnnormalContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class UnnormalModle implements UnnormalContract.Model {


    private ApiService service;


    public UnnormalModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void declare(String serialNo, String content, Callback callback) {
        Call call = service.declare(serialNo, content);
        call.enqueue(callback);
    }
}
