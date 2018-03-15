package com.qeasy.samrtlockb.base.p;

import android.text.TextUtils;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.LoginContract;
import com.qeasy.samrtlockb.bean.User;


/**
 * Created by Administrator on 2016/12/5.
 */

public class LoginPresenter extends LoginContract.Presenter {





    @Override
    public void onAttached() {

    }

    @Override
    public void getTelCode(String phone) {
        if(TextUtils.isEmpty(phone) || phone.length()!=11){
            mView.showMsg("请正确输入手机号码");
            return;
        }

        mModel.getTelCode(phone, new PostCallback<LoginContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView == null)
                    return;
                mView.showMsg("短信验证码已发送,请注意查收");
                mView.countDownStart();
            }

        });
    }

    @Override
    public void login(String phone, String code) {
        if(TextUtils.isEmpty(phone) || phone.length()!=11){
            mView.showMsg("请正确输入手机号码");
            return;
        }
        if(TextUtils.isEmpty(code)){
            mView.showMsg("验证码不能为空");
            return;
        }

        mModel.login(phone, code, new PostCallback<LoginContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() == null)
                    return;
                if (api.getT() instanceof User) {
                    User user = (User) api.getT();
                    if (mView != null) {
                        if (user != null && !TextUtils.isEmpty(user.getAccessToken())) {
                            mView.loginSuccess();
                            mView.saveLoginInfo(user);
                        }
                    }

                }
            }
        });
    }
}
