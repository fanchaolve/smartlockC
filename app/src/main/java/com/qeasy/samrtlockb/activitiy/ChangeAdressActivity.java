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
import com.qeasy.samrtlockb.base.m.AddressModle;
import com.qeasy.samrtlockb.base.p.AddressPresenter;
import com.qeasy.samrtlockb.base.v.AddressContract;
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
public class ChangeAdressActivity extends BaseActivity
        <AddressPresenter,AddressModle>implements AddressContract.View{


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;

    @BindView(R.id.iv_right)
    ImageView iv_right;


    @BindView(R.id.tv_confim)
    TextView tv_confim;

    @BindView(R.id.et_address)
    EditText et_address;


    private String serialNo = "";

    private String address = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_changeaddress;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.detailaddress));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.icon_dingwei);

    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address = et_address.getText().toString();
                if (address == null || address.trim().length() == 0) {
                    showMsg("未获取位置信息");
                    return;
                }

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

            if (bundle.containsKey(Constants.ADDR)) {
                address = bundle.getString(Constants.ADDR);
                et_address.setText(address);
            }


        }
    }

    @Override
    public void changeInfoSucess() {
        finish();
    }
}
