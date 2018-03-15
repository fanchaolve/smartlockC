package com.qeasy.samrtlockb.base.p;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.AddressContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;


/**
 * Created by fancl.
 */

public class AddressPresenter extends AddressContract.Presenter {


    @Override
    public void onAttached() {

    }

    @Override
    public void changeInfo(String serialNo, String address) {
        mModel.changeInfo(serialNo, address, new PostCallback<AddressContract.View>(mView, true) {
            @Override
            public void successCallback(Result_Api api) {
                if (mView != null) {
                    mView.showMsg("修改成功");
                    mView.changeInfoSucess();
                }

            }
        });

    }


}
