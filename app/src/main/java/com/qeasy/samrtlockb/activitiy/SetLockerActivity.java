package com.qeasy.samrtlockb.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.UserLoginPageAdapter;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.fragment.LockerFragment;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 设置开锁许可人员
 */
public class SetLockerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.title_name)
    TextView title_name;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tablayout)
    SlidingTabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.edittext)
    EditText edittext;

    private PagerAdapter adapter;
    private List<Fragment> mFragments;
    private List<String> mTitleList;

    private String serialNo = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_locker;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(R.string.set_locker);
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.icon_add);
        initFragment();
    }

    public void initFragment() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey(Constants.SERINO)) {
                serialNo = bundle.getString(Constants.SERINO);
            }


        }
        mTitleList = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTitleList.add("当前使用人员");
        mTitleList.add("历史使用人员");
        mFragments.add(LockerFragment.newInstance(0,serialNo));
        mFragments.add(LockerFragment.newInstance(1,serialNo));

        adapter = new UserLoginPageAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        viewpager.setAdapter(adapter);
        tablayout.setViewPager(viewpager);


        //可实时设置标签数据
//        mTitleList.set(0,"当前使用人员");
    }

    @Override
    public void initListener() {
        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle =new Bundle();
                bundle.putString(Constants.SERINO,serialNo);
                Navigation.showAddmember(bundle);
            }
        });

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initdata() {

    }
}
