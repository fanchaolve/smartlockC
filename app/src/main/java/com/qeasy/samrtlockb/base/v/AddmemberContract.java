package com.qeasy.samrtlockb.base.v;

import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.bean.Member;

import retrofit2.Callback;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface AddmemberContract {


    interface Model extends BaseModel {


        void member_smartlock_add(String serialNo,
                                  String mobile,
                                  String realName,
                                  String identityCard,
                                  String useStartTime,
                                  String useEndTime,
                                  int isPinCode,
                                  int isIcCode,
                                  int isFingerprintCode,
                                  int frequency,
                                  int frequencyMode, Callback callback);

        void member_smartlock_edit(String serialNo,
                                  String mobile,
                                  String realName,
                                  String identityCard,
                                  String useStartTime,
                                  String useEndTime,
                                  int isPinCode,
                                  int isIcCode,
                                  int isFingerprintCode,
                                  int frequency,
                                  int frequencyMode,
                                   String id,
                                   Callback callback);

    }


    interface View extends BaseView {

        void saveSucess();

        void info(Member member);
    }

    abstract class Presenter extends BasePresenter<AddmemberContract.Model, AddmemberContract.View> {


        public abstract void save(String serialNo,
                                  String mobile,
                                  String realName,
                                  String identityCard,
                                  String useStartTime,
                                  String useEndTime,
                                  int isPinCode,
                                  int isIcCode,
                                  int isFingerprintCode,
                                  int frequency,
                                  int frequencyMode,String id);

        public abstract void setType(int type);


    }
}
