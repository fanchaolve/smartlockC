package com.qeasy.samrtlockb.base.v;


import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.MemberResult;

import retrofit2.Callback;

public interface LockerContract {

    interface Model extends BaseModel {

        void member_smartlock_search(
                String serialNo,
                int smartlockId,
                int status,
                int offset,
                int limit,
                String param, Callback callback);

        void member_smartlock_stop(String id,Callback callback);

        void member_smartlock_start(String id,Callback callback);
    }

    interface View extends BaseView {

        void refresh(MemberResult result);

        void loadMore(MemberResult result);

        void stopSuccess();
        void startSuccess();

        void failrefresh();
    }

    abstract class Presenter extends BasePresenter<LockerContract.Model, LockerContract.View> {

        public abstract  void member_smartlock_search(int state,
                String serialNo,
                int status,
                String param);

        public abstract  void member_smartlock_stop(String id);

        public abstract  void member_smartlock_start(String id);

    }
}
