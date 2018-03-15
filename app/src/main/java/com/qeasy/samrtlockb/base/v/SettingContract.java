package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface SettingContract {


    interface Model extends BaseModel {


        //认证频段
        void merchant_smartlock_changeInfo(String serialNo, int frequency, int frequencyMode,
                                           Callback callback);

        //重置
        void merchant_smartlock_unBinding(String serialNo,
                                          Callback callback);

        //停用
        void smartlock_stop(String serialNo,
                                          Callback callback);







    }


    interface View extends BaseView {


        void changeInfoSuccess();



    }

    abstract class Presenter extends BasePresenter<SettingContract.Model, SettingContract.View> {


        public abstract void changeInfo(String serialNo, int frequency, int frequencyMode);

        public abstract void unBinding(String serialNo);

        public abstract void stop(String serialNo);








    }
}
