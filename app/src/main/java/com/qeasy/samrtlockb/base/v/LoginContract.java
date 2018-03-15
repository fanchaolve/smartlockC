package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.User;

import retrofit2.Callback;

/**
 * Created by Administrator on 2016/12/5.
 */

public interface LoginContract {


    interface Model extends BaseModel {

        void getTelCode(String phone, Callback callback);

        void login(String phone, String code, Callback callback);

    }


    interface View  extends BaseView{


        void countDownStart();

        void countDownEnd();

        void loginSuccess();

        void saveLoginInfo(User info);




    }

    abstract class Presenter extends BasePresenter<Model, View> {


        /**
         *
         *
         * @param phone 手机号码
         */
        public abstract void getTelCode(String phone);

        public abstract void login(String phone, String code);

    }
}
