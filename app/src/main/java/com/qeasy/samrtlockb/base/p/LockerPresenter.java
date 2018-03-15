package com.qeasy.samrtlockb.base.p;

import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.HomeContract;
import com.qeasy.samrtlockb.base.v.LockerContract;
import com.qeasy.samrtlockb.bean.MemberResult;
import com.qeasy.samrtlockb.utils.Constants;

public class LockerPresenter extends LockerContract.Presenter{


    private int offset = 0;//起始位置

    private static final int limit = 5;//一页条数

    @Override
    public void onAttached() {

    }


    @Override
    public void member_smartlock_search(final int state, String serialNo, int status, String param) {
        if (state == Constants.FRESH)
            offset = 0;

        mModel.member_smartlock_search(serialNo, 1, status,offset, limit,param, new PostCallback<LockerContract.View>(mView) {
            @Override
            public void successCallback(Result_Api api) {
                MemberResult result = (MemberResult) api.getT();
                if (mView != null && result != null) {
                    if (result.getRows() != null ) {
                        offset += result.getRows().size();
                    }
                    if (state == Constants.FRESH) {
                        mView.refresh(result);
                    } else {
                        mView.loadMore(result);
                    }

                }
            }
            @Override
            public void failCallback() {
                super.failCallback();
                if(mView!=null) {
                    mView.failrefresh();
                }
            }
        });
    }

    @Override
    public void member_smartlock_stop(String id) {
        mModel.member_smartlock_stop(id, new PostCallback<LockerContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView!= null) {
                    mView.showMsg("停用成功");
                    mView.stopSuccess();
                }
            }
        });
    }

    @Override
    public void member_smartlock_start(String id) {
        mModel.member_smartlock_start(id, new PostCallback<LockerContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if(mView!= null) {
                    mView.showMsg("启用成功");
                    mView.startSuccess();
                }
            }
        });

    }
}
