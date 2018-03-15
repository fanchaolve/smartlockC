package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.HomeContract;
import com.qeasy.samrtlockb.base.v.NoticeContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class NoticeModle implements NoticeContract.Model {


    private ApiService service;



    public NoticeModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }

    @Override
    public void notice_search(int offset, int limit, Callback callback) {
        Call call =service.notice_search(offset,limit);
        call.enqueue(callback);
    }


}
