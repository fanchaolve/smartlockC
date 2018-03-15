package com.qeasy.samrtlockb.activitiy;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.activitiy
 * <p>
 * 说明：问题反馈
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/7
 * <p>
 * ==============================================
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener{




    @Override
    public int getLayoutId() {
        return 0;

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                finish();
                break;
        }
    }
}
