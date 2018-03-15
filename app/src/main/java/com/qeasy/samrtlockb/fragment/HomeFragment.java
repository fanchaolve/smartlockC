package com.qeasy.samrtlockb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.qeasy.samrtlockb.base.BaseFragment;


/**
 * Created by Administrator on 2016/12/20.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
    }

    @Override
    protected void initdate(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }





    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        }
    }

    @Override
    public void invalidToke() {

    }
}
