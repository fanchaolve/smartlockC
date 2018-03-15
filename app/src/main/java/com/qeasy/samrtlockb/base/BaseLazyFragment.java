package com.qeasy.samrtlockb.base;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.base.v.BaseView;
import com.qeasy.samrtlockb.utils.InstanceUtil;
import com.qeasy.samrtlockb.utils.LoadingUtil;

import butterknife.Unbinder;

//import butterknife.Unbinder;

/**
 * 懒惰式加载fragment基础类
 * Created by fancl
 */

public abstract class BaseLazyFragment<P extends BasePresenter, M extends BaseModel> extends Fragment
        implements BaseView {


    // Fragment的根View
    private View mRootView;
    // 检测声明周期中，是否已经构建视图
    private boolean mViewCreated = false;


    public Unbinder unbinder;

    public String TAG ;


    public P mPresenter;
    public Context mContext;
//    private Unbinder unbinder;

    private boolean mUserVisible = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        TAG = getClass().getSimpleName();
        mPresenter = InstanceUtil.getInstance(this, 0);
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return null;
        }

        if (mRootView != null) {
            mViewCreated = true;
            return mRootView;
        }

        final Context context = inflater.getContext();
        FrameLayout root = new FrameLayout(context);
        View view = inflater.inflate(getLayoutId(), container, false);

//        mViewStub.setLayoutResource(getLayoutId());
        root.addView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//        root.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));

        mRootView = root;
        mViewCreated = true;
        initView(mRootView);
        if (mUserVisible) {
            realLoad();
        }

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewCreated = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }



    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

    /**
     * 提示信息
     *
     * @param aFormatMsg
     * @param aMsgArgs
     */
    public void showTip(String aFormatMsg, Object... aMsgArgs) {
        String outString = String.format(aFormatMsg, aMsgArgs);
        int duration = (outString.length() > 10) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(mContext, outString, duration).show();
    }

    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();


    @Override
    public void showMsg(String msg) {
        showTip(msg);
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoading() {
        LoadingUtil.showLoading(mContext);
    }

    @Override
    public void dissmissLoading() {
        LoadingUtil.hideLoading();
    }

    @Override
    public void updateLoading() {

    }

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUserVisible = isVisibleToUser;
        if (mUserVisible && mViewCreated) {
            realLoad();
        }
    }


    // 判断是否已经加载
    private boolean mLoaded = false;

    /**
     * 控制只允许加载一次
     */
    private void realLoad() {
        if (mLoaded) {
            return;
        }

        mLoaded = true;
        onRealViewLoaded();
    }

    /**
     * 当视图真正加载时调用
     */
    protected abstract void onRealViewLoaded();

    /**
     * 初始化布局
     * @param rootView
     */
    protected abstract void initView(View rootView);


}
