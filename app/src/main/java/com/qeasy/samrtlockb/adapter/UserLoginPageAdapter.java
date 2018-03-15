package com.qeasy.samrtlockb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.adapter
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/1/10
 * <p>
 * ==============================================
 */

public class UserLoginPageAdapter extends FragmentPagerAdapter {


    private List<Fragment> list;
    private List<String> titles;

    public UserLoginPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public UserLoginPageAdapter(FragmentManager fm, List<Fragment> list, List<String> titles) {
        this(fm, list);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null || titles.size() == 0) {
            return null;
        }
        return titles.get(position % titles.size());
    }
}
