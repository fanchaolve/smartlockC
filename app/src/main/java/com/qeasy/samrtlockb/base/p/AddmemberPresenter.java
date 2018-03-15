package com.qeasy.samrtlockb.base.p;


import com.qeasy.samrtlockb.api.PostCallback;
import com.qeasy.samrtlockb.api.Result_Api;
import com.qeasy.samrtlockb.base.v.AddmemberContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;


/**
 * Created by fancl.
 */

public class AddmemberPresenter extends AddmemberContract.Presenter {


    private int type = 0;//0为添加，1为编辑

    @Override
    public void onAttached() {

    }


    @Override
    public void save(String serialNo, String mobile, String realName, String identityCard, String useStartTime, String useEndTime, int isPinCode, int isIcCode, int isFingerprintCode, int frequency, int frequencyMode, String id) {

        if (type == 0)
            mModel.member_smartlock_add(serialNo, mobile.trim(), realName.trim(),
                    identityCard.trim(), useStartTime, useEndTime, isPinCode, isIcCode, isFingerprintCode, frequency, frequencyMode,
                    new PostCallback<AddmemberContract.View>(mView, true) {
                        @Override
                        public void successCallback(Result_Api api) {
                            if (mView != null) {
                                mView.showMsg("添加成功");
                                mView.saveSucess();
                            }
                        }
                    });
        else
            mModel.member_smartlock_edit(serialNo, mobile.trim(), realName.trim(),
                    identityCard.trim(), useStartTime, useEndTime, isPinCode, isIcCode, isFingerprintCode, frequency, frequencyMode, id,
                    new PostCallback<AddmemberContract.View>(mView, true) {
                        @Override
                        public void successCallback(Result_Api api) {
                            if (mView != null) {
                                mView.showMsg("修改成功");
                                mView.saveSucess();
                            }
                        }
                    });


    }

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
