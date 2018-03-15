package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;

import retrofit2.Callback;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface TemporarypassContract {


    interface Model extends BaseModel {



        void changeInfo(String serialNo,
                        String pinCode,
                        String fingerprintCode,
                        String icCode,Callback callback);

    }


    interface View extends BaseView {


        void changeInfoSucess();
    }

    abstract class Presenter extends BasePresenter<TemporarypassContract.Model, TemporarypassContract.View> {


       public abstract void changeInfo(String serialNo, String pinCode,String fingerprintCode,
                                       String icCode);
    }
}
