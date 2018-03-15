package com.qeasy.samrtlockb.base.p;


import android.text.TextUtils;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.HomeContract;
import com.qeasy.samrtlockb.bean.SmarLock;

import java.util.List;


/**
 * Created by fancl.
 */

public class HomePresenter extends HomeContract.Presenter {

    @Override
    public void onAttached() {

    }


    @Override
    public void homeData() {
        mModel.home(new PostCallback<HomeContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if( mView != null){
                    List<SmarLock> list = (List<SmarLock>) api.getT();
                    if(list!= null && list.size()>0)
                        mView.getHomeData(list.get(0));
                    else
                        mView.getNoneData();
                }
            }
        });
    }

    @Override
    public void tel() {
        mModel.tel(new PostCallback<HomeContract.View>(mView,true) {
            @Override
            public void successCallback(Result_Api api) {
                if( mView != null){
                    String tel = (String) api.getT();
                    if(!TextUtils.isEmpty(tel)){
                        mView.getTel(tel);
                    }
                }
            }
        });
    }
}
