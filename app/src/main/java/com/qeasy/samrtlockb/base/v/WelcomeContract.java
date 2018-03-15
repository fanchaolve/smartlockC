package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;




/**
 * Created by Administrator on 2017/1/9.
 */

public interface WelcomeContract {


    interface Model extends BaseModel {




    }


    interface View extends BaseView {







    }

    abstract class Presenter extends BasePresenter<WelcomeContract.Model, WelcomeContract.View> {





    }
}
