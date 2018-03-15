package com.qeasy.samrtlockb.activitiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.SettingModle;
import com.qeasy.samrtlockb.base.p.SettingPresenter;
import com.qeasy.samrtlockb.base.v.SettingContract;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.DataUtils;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.AbstactDailog;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.activitiy
 * <p>
 * 说明：设置
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/6
 * <p>
 * ==============================================
 */

public class SettingActivity extends BaseActivity<SettingPresenter, SettingModle>
        implements View.OnClickListener, SettingContract.View {





    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;




    @BindView(R.id.title_back)
    ImageView title_back;

    @BindView(R.id.lin_reset)
    LinearLayout lin_reset;

    @BindView(R.id.lin_editpassword)
    LinearLayout lin_editpassword;

    @BindView(R.id.lin_editfingerprint)
    LinearLayout lin_editfingerprint;






    private String frequencyStr = "";


    private String serialNo = "";


    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.setting));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);


    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(this);
        lin_editpassword.setOnClickListener(this);
        lin_editfingerprint.setOnClickListener(this);
        lin_reset.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.lin_editpassword://修改密码
                Bundle bundle =new Bundle();
                bundle.putString(Constants.SERINO,serialNo);
                Navigation.showChangePassword(bundle);
                break;
            case R.id.lin_editfingerprint://修改指纹
                bundle =new Bundle();
                bundle.putString(Constants.SERINO,serialNo);
                Navigation.showFigerp(bundle);
                break;
            case R.id.lin_reset:
                new AbstactDailog(SettingActivity.this).setContent("您确定要退出登录吗？")
                        .setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Navigation.showLogin(SettingActivity.this,null);
                            }
                        },getString(R.string.confim1))
                        .setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        },getString(R.string.cancel)).show();
                break;


        }
    }

    @Override
    public void changeInfoSuccess() {

    }
}
