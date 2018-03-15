package com.qeasy.samrtlockb.activitiy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.TempPasswordModle;
import com.qeasy.samrtlockb.base.p.TempPasswoldPresenter;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.TextviewObserver;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：设置指纹
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/9
 * <p>
 * ==============================================
 */
public class FingerprintActivity extends BaseActivity <TempPasswoldPresenter,TempPasswordModle>
        implements TemporarypassContract.View{


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

    @BindView(R.id.tv_left_fp1)
    TextView tv_left_fp1;

    @BindView(R.id.tv_left_fp2)
    TextView tv_left_fp2;


    @BindView(R.id.tv_left_fp3)
    TextView tv_left_fp3;

    @BindView(R.id.tv_right_fp1)
    TextView tv_right_fp1;

    @BindView(R.id.tv_right_fp2)
    TextView tv_right_fp2;


    @BindView(R.id.tv_right_fp3)
    TextView tv_right_fp3;



    @BindView(R.id.lin_fp1)
    LinearLayout lin_fp1;

    @BindView(R.id.lin_fp2)
    LinearLayout lin_fp2;

    @BindView(R.id.lin_fp3)
    LinearLayout lin_fp3;

    private final static  int REQUESTCODE1 =0x100;
    private final static  int REQUESTCODE2 =0x101;
    private final static  int REQUESTCODE3 =0x102;

    private int type;

    private String serialNo = "";



    @Override
    public int getLayoutId() {
        return R.layout.activity_fingerprint;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.setf));
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

        lin_fp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showFigerping(null,REQUESTCODE1);
            }
        });

        lin_fp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showFigerping(null,REQUESTCODE2);
            }
        });

        lin_fp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showFigerping(null,REQUESTCODE3);
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
        if(requestCode == REQUESTCODE1 && resultCode == Activity.RESULT_OK){
            type = REQUESTCODE1;
            mPresenter.changeInfo(serialNo,"","00001","");
        }

        if(requestCode == REQUESTCODE2 && resultCode == Activity.RESULT_OK){
            type = REQUESTCODE2;
            mPresenter.changeInfo(serialNo,"","00002","");
        }

        if(requestCode == REQUESTCODE2 && resultCode == Activity.RESULT_OK){
            type = REQUESTCODE3;
            mPresenter.changeInfo(serialNo,"","00003","");
        }
    }

    @Override
    public void changeInfoSucess() {
        showMsg("指纹录制成功");
        if(type == REQUESTCODE1){
            tv_left_fp1.setText(getString(R.string.isfp));
            tv_right_fp1.setText(getString(R.string.change));
        }else if(type ==REQUESTCODE2){
            tv_left_fp2.setText(getString(R.string.isfp));
            tv_right_fp2.setText(getString(R.string.change));
        }else if(type ==REQUESTCODE3){
            tv_left_fp3.setText(getString(R.string.isfp));
            tv_right_fp3.setText(getString(R.string.change));
        }else {

        }
    }
}
