package com.qeasy.samrtlockb.activitiy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.AddressModle;
import com.qeasy.samrtlockb.base.m.TempPasswordModle;
import com.qeasy.samrtlockb.base.p.AddressPresenter;
import com.qeasy.samrtlockb.base.p.TempPasswoldPresenter;
import com.qeasy.samrtlockb.base.v.AddressContract;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/8
 * <p>
 * ==============================================
 */
public class IcSmartLockActivity extends BaseActivity
        <TempPasswoldPresenter,TempPasswordModle>implements TemporarypassContract.View{


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;


    @BindView(R.id.iv_blstate)
    ImageView iv_blstate;

    @BindView(R.id.tv_statememo)
    TextView tv_statememo;

    @BindView(R.id.tv_pz)
    TextView tv_pz;

    @BindView(R.id.lin_setic)
    LinearLayout lin_setic;





    private String serialNo = "";

    private static final  int GOSETTING  =0x001;//requestcode


    @Override
    public int getLayoutId() {
        return R.layout.activity_ic;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.icsettitle));
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

        iv_blstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        lin_setic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showicSetting(null,GOSETTING);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOSETTING && resultCode == Activity.RESULT_OK){
            mPresenter.changeInfo(serialNo,"","","0123456");
        }
    }

    @Override
    public void changeInfoSucess() {
        showMsg("配置成功");
        tv_pz.setText("更改");
    }
}
