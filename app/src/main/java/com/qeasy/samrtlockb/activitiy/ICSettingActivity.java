package com.qeasy.samrtlockb.activitiy;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.widget.TextviewObserver;

import java.util.logging.Handler;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.activitiy
 * <p>
 * 说明：ic卡配置中
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/7
 * <p>
 * ==============================================
 */

public class ICSettingActivity extends BaseActivity implements View.OnClickListener{




    @BindView(R.id.tv_cancel)
    TextView tv_cancel;



    @Override
    public int getLayoutId() {
        return R.layout.activity_icsetting;

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initdata() {

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(Activity.RESULT_OK);
                finish();
            }
        },5000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
