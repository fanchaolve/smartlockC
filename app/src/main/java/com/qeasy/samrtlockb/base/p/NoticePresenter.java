package com.qeasy.samrtlockb.base.p;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.NoticeContract;
import com.qeasy.samrtlockb.bean.NoticeResult;
import com.qeasy.samrtlockb.utils.Constants;


/**
 * Created by fancl.
 */

public class NoticePresenter extends NoticeContract.Presenter {



    private int offset = 0;//起始位置

    private static final int limit = 5;//一页条数


    @Override
    public void onAttached() {

    }






    @Override
    public void notice_search(final int state) {
        if (state == Constants.FRESH)
            offset = 0;

        mModel.notice_search(offset, limit, new PostCallback<NoticeContract.View>(mView) {
            @Override
            public void successCallback(Result_Api api) {
                NoticeResult result = (NoticeResult) api.getT();
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
}
