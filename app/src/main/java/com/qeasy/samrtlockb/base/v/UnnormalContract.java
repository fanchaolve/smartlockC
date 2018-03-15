package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface UnnormalContract {


    interface Model extends BaseModel {

        void declare(String serialNo,
                        String content, Callback callback);

    }


    interface View extends BaseView {


        void successInfo();
    }

    abstract class Presenter extends BasePresenter<UnnormalContract.Model, UnnormalContract.View> {


       public abstract void declare(String serialNo,
                                    String content);
    }
}
