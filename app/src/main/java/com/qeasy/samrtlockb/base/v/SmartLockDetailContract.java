package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.SmarLock;
import com.qeasy.samrtlockb.bean.SmartlockRecordResult;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface SmartLockDetailContract {


    interface Model extends BaseModel {


        void smartlock_banner(String serialNo,Callback callback);


        void unlockrecord_search(String serialNo,
                                 int offset,
                                 int limit,
                                 String param,
                                 String startUnlockTime,
                                 String endUnlockTime,
                                 String memberId,
                                 Callback callback);





    }


    interface View extends BaseView {



        void getDetail_banner(SmarLock smarLock);

        void refresh(SmartlockRecordResult result);

        void loadMore(SmartlockRecordResult result);

        void failrefresh();




    }

    abstract class Presenter extends BasePresenter<SmartLockDetailContract.Model, SmartLockDetailContract.View> {

        public abstract void smartlock_banner();

        public abstract void setSerialNo(String serialNo);

        public abstract void unlockrecord_search(int state,String param,
                                                 String startUnlockTime,
                                                 String endUnlockTime);



    }
}
