package com.qeasy.samrtlockb.base.p;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.SettingContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;


/**
 * Created by fancl.
 */

public class TempPasswoldPresenter extends TemporarypassContract.Presenter {





    @Override
    public void onAttached() {

    }

    @Override
    public void changeInfo(String serialNo, String pinCode,String fingerprintCode,
                           String icCode) {
                mModel.changeInfo(serialNo, pinCode, fingerprintCode,icCode, new PostCallback<TemporarypassContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView != null) {
                    //mView.showMsg("修改成功");
                    mView.changeInfoSucess();
                }

            }
        });
    }





}
