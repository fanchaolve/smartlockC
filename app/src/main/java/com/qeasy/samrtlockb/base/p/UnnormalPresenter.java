package com.qeasy.samrtlockb.base.p;

import android.text.TextUtils;

import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.LoginContract;
import com.qeasy.samrtlockb.base.v.UnnormalContract;
import com.qeasy.samrtlockb.bean.User;


/**
 * Created by Administrator on 2016/12/5.
 */

public class UnnormalPresenter extends UnnormalContract.Presenter {





    @Override
    public void onAttached() {

    }


    @Override
    public void declare(String serialNo, String content) {
        mModel.declare(serialNo, content, new PostCallback<UnnormalContract.View>(
                mView,true
        ) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView!= null)
                    mView.successInfo();
            }
        });
    }
}
