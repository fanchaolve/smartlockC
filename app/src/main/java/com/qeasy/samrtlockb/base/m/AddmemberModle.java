package com.qeasy.samrtlockb.base.m;

import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.v.AddmemberContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AddmemberModle implements AddmemberContract.Model {


    private ApiService service;



    public AddmemberModle() {
        service = RetrofitFactory.getINSTANCE().create(ApiService.class);
    }


    @Override
    public void member_smartlock_add(String serialNo, String mobile, String realName, String identityCard, String useStartTime, String useEndTime, int isPinCode, int isIcCode, int isFingerprintCode, int frequency, int frequencyMode, Callback callback) {
        Call call =service.member_smartlock_add(
                serialNo,mobile,realName,identityCard,useStartTime,useEndTime,isPinCode,isIcCode,
                isFingerprintCode,frequency,frequencyMode);
        call.enqueue(callback);
    }

    @Override
    public void member_smartlock_edit(String serialNo, String mobile, String realName, String identityCard, String useStartTime, String useEndTime, int isPinCode, int isIcCode, int isFingerprintCode, int frequency, int frequencyMode, String id, Callback callback) {
        Call call =service.member_smartlock_edit(
                serialNo,mobile,realName,identityCard,useStartTime,useEndTime,isPinCode,isIcCode,
                isFingerprintCode,frequency,frequencyMode,id);
        call.enqueue(callback);
    }

}
