package com.qeasy.samrtlockb.activitiy;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明 开锁记录
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/14
 * <p>
 * ==============================================
 */
public class SmartLockRecordActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;


    @BindView(R.id.mRecyclerView)
    LRecyclerView mRecyclerView;


    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;



    @Override
    public int getLayoutId() {
        return R.layout.activity_smartlockrecord;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.notice));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
}
