package com.qeasy.samrtlockb.activitiy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.TempPasswordModle;
import com.qeasy.samrtlockb.base.p.TempPasswoldPresenter;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：开锁密码
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/6
 * <p>
 * ==============================================
 */
public class TemporarypasswordActivity extends BaseActivity
        <TempPasswoldPresenter, TempPasswordModle> implements TemporarypassContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;


    @BindView(R.id.tv_confim)
    TextView tv_confim;

    @BindView(R.id.et_password)
    EditText et_password;

    private String serialNo = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_temppassword;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.unlock_password));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);
    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et_password.getText().toString();
                if(password == null || password.trim().length()== 0){
                    showMsg("请输入新的开锁密码");
                    return;
                }
                mPresenter.changeInfo(serialNo,password.trim(),"","");
            }
        });
    }

    @Override
    public void initdata() {

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey(Constants.SERINO)) {
                serialNo = bundle.getString(Constants.SERINO);
            }


        }
    }

    @Override
    public void changeInfoSucess() {
        showMsg("修改成功");
        finish();
    }
}
