package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.SmarLock;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface HomeContract {


    interface Model extends BaseModel {




        void home(Callback callback);


        void tel(Callback callback);

    }


    interface View extends BaseView {




        void getHomeData(SmarLock smarLock);

        void getNoneData();


        void changeViewStyle(boolean isUse);

        void getTel(String tel);


    }

    abstract class Presenter extends BasePresenter<HomeContract.Model, HomeContract.View> {


        public abstract void homeData();
        public abstract void tel();

    }
}
