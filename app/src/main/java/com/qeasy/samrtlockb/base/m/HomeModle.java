package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.HomeContract;


import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class HomeModle implements HomeContract.Model {


    private ApiService service;



    public HomeModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void home(Callback callback) {
        Call call =service.home();
        call.enqueue(callback);
    }

    @Override
    public void tel(Callback callback) {
        Call call =service.tel();
        call.enqueue(callback);
    }
}
