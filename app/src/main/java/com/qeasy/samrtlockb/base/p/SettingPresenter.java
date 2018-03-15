package com.qeasy.samrtlockb.base.p;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.SettingContract;


/**
 * Created by fancl.
 */

public class SettingPresenter extends SettingContract.Presenter {





    @Override
    public void onAttached() {

    }


    @Override
    public void changeInfo(String serialNo, int frequency, int frequencyMode) {
        mModel.merchant_smartlock_changeInfo(serialNo, frequency, frequencyMode, new PostCallback<SettingContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView!= null) {
                    mView.showMsg("修改成功");
                    mView.changeInfoSuccess();
                }
            }
        });
    }

    @Override
    public void unBinding(String serialNo) {
        mModel.merchant_smartlock_unBinding(serialNo, new PostCallback<SettingContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView == null)
                    return;
                mView.showMsg("成功");
            }
        });
    }

    @Override
    public void stop(String serialNo) {
        mModel.smartlock_stop(serialNo, new PostCallback<SettingContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView != null)
                    mView.showMsg("停用成功");


            }
        });
    }
}
