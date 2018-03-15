package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface AddressContract {


    interface Model extends BaseModel {



        void changeInfo(String serialNo,
                        String address,Callback callback);

    }


    interface View extends BaseView {


        void changeInfoSucess();
    }

    abstract class Presenter extends BasePresenter<AddressContract.Model, AddressContract.View> {


       public abstract void changeInfo(String serialNo, String address);
    }
}
