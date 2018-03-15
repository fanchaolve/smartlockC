package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.NoticeResult;

import retrofit2.Callback;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface NoticeContract {


    interface Model extends BaseModel {




        void notice_search(int offset, int limit, Callback callback);



    }


    interface View extends BaseView {


        void refresh(NoticeResult result);

        void loadMore(NoticeResult result);

        void failrefresh();



    }

    abstract class Presenter extends BasePresenter<NoticeContract.Model, NoticeContract.View> {


        public abstract void notice_search(int state);

    }
}
