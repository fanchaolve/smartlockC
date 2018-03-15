package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.WelcomeContract;

import retrofit2.Call;
import retrofit2.Callback;


public class WelcomeModle implements WelcomeContract.Model {


    private ApiService service;



    public WelcomeModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }



}
