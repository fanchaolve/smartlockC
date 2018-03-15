package com.qeasy.samrtlockb.activitiy.login;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;

import com.qeasy.samrtlockb.activitiy.HomeActivity;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.LoginModel;
import com.qeasy.samrtlockb.base.p.LoginPresenter;
import com.qeasy.samrtlockb.base.v.LoginContract;
import com.qeasy.samrtlockb.bean.User;
import com.qeasy.samrtlockb.listener.PhoneTextWatcher2;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.PreferenceUtil;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.CountDownTextView;
import com.qeasy.samrtlockb.widget.LoginTelEdit;
import com.qeasy.samrtlockb.widget.TextviewObserver;

import butterknife.BindView;


/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity
        <LoginPresenter, LoginModel>implements View.OnClickListener,LoginContract.View {




    @BindView(R.id.tv_confim)
    TextView tv_confim;

    @BindView(R.id.et_code)
    EditText et_code;

    @BindView(R.id.tv_getcode)
    CountDownTextView tv_getcode;

    @BindView(R.id.et_phone)
    LoginTelEdit et_phone;

    @BindView(R.id.top_view)
    View top_view;



    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, top_view, true);
    }

    @Override
    public void initListener() {
        tv_getcode.setOnClickListener(this);
        tv_confim.setOnClickListener(this);
        et_phone.addTextChangedListener(new PhoneTextWatcher2(et_phone, tv_getcode, et_code));

    }

    @Override
    public void initdata() {
        AppManager.getInstance().deleteUser();
        String phone =PreferenceUtil.getSharedPreference(mContext,PreferenceUtil.PHONE);
        StringBuffer buffer = new StringBuffer(phone);
        if(phone.length() ==11) {
            buffer.insert(3, " ").insert(8, " ");
            et_phone.setText(buffer.toString());
        }
        et_phone.setText(buffer.toString());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_getcode:
                mPresenter.getTelCode(et_phone.getText().toString().replace(" ", ""));
                break;
            case R.id.tv_confim:
                mPresenter.login(et_phone.getText().toString().replace(" ", ""),et_code.getText().toString());
        }
    }


    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                showTip(getString(R.string.snack_exit_once_more));
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                AppManager.getInstance().AppExit(this);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void countDownStart() {
        tv_getcode.startTime();
    }

    @Override
    public void countDownEnd() {
        tv_getcode.cancelTime();
    }

    @Override
    public void loginSuccess() {
        PreferenceUtil.saveSharedPreference(mContext,PreferenceUtil.PHONE,et_phone.getText().toString().replace(" ", ""));


    }

    @Override
    public void saveLoginInfo(User info) {
        AppManager.getInstance().addUser(info);
        if(TextUtils.isEmpty(info.getSerialNo())){
            Navigation.showbinding1(null);
            return;
        }
        Navigation.showHome(null);
        finish();
    }
}
