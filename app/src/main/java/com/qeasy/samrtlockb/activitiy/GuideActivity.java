package com.qeasy.samrtlockb.activitiy;


import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.GuidePageAdapter;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.PreferenceUtil;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GuideActivity extends BaseActivity {









    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
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

    /**
     * 实现再按一次退出提醒
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
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
}
