package com.qeasy.samrtlockb.base;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qeasy.samrtlockb.widget.AbstactDailog;
import com.umeng.analytics.MobclickAgent;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.base.v.BaseView;
import com.qeasy.samrtlockb.utils.InstanceUtil;
import com.qeasy.samrtlockb.utils.LoadingUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基础类
 * Created by fancl
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment
 implements BaseView{


    public String TAG ;
    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    public ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);

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
        if(getLayoutId()==0){
            return null;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdate(savedInstanceState);
    }


    public void onStart() {
        super.onStart();

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();


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

    /**
     * 数据填充
     */

    public abstract void initView();

    protected abstract void initdate(Bundle savedInstanceState);

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

    public AbstactDailog dialog ;
    @Override
    public void invalidToke() {
        if(dialog == null || !dialog.isShowing()){
            dialog =new AbstactDailog(getActivity());
            dialog.setTitle(getString(R.string.invalidtoke)).
                    setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.showLogin(getActivity(),null);
                            dialog.dismiss();
                        }
                    }, getString(R.string.confim1)).show();
        }
    }







}
