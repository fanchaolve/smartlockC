package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.LockerContract;

import retrofit2.Call;
import retrofit2.Callback;


public class LockerModle implements LockerContract.Model {


    private ApiService service;



    public LockerModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void member_smartlock_search(String serialNo, int smartlockId, int status, int offset, int limit, String param, Callback callback) {
        Call call =service.member_smartlock_search(serialNo,smartlockId,status,offset,limit,param);
        call.enqueue(callback);
    }

    @Override
    public void member_smartlock_stop(String id, Callback callback) {
        Call call =service.member_smartlock_stop(id);
        call.enqueue(callback);
    }

    @Override
    public void member_smartlock_start(String id, Callback callback) {
        Call call =service.member_smartlock_start(id);
        call.enqueue(callback);
    }
}
