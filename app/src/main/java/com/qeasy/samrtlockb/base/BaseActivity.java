package com.qeasy.samrtlockb.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.qeasy.samrtlockb.MyApplication;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.api.ApiService;
import com.qeasy.samrtlockb.api.RetrofitFactory;
import com.qeasy.samrtlockb.base.m.BaseModel;
import com.qeasy.samrtlockb.base.p.BasePresenter;
import com.qeasy.samrtlockb.base.v.BaseView;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.InstanceUtil;
import com.qeasy.samrtlockb.utils.LoadingUtil;
import com.qeasy.samrtlockb.widget.AbstactDailog;
import com.umeng.analytics.MobclickAgent;
import com.qeasy.samrtlockb.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity
        implements BaseView {


    public String TAG;
    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    private boolean isCheck = true;//检测是否第一次
    public ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);


    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        this.setContentView(getLayoutId());
        StatusBarUtil.initStatusBar(this);
        mContext = this;
        TAG = this.getComponentName().getClassName();
//        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);

        setDisplayMetrics();
        unbinder = ButterKnife.bind(this);
        mPresenter = InstanceUtil.getInstance(this, 0);
        this.initView();
        this.initListener();
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
        this.initdata();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatusBarUtil.destoryStatusBar(this);
        if (mPresenter != null)
            mPresenter.onDetached();
        if (unbinder != null)
            unbinder.unbind();
        AppManager.getInstance().finishActivity(this);

    }

    @Override
    public void finish() {
        closeKeyboard();
        super.finish();
//		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    //关闭
    public void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //// TODO: 2016/11/25  填写 你需要在底栈的页面
//            return (AppManager.getInstance().processBackKey(LoginActivity.class) ?
//                    true : super.onKeyDown(keyCode, event));
//
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }

    //求屏幕尺寸
    public void setDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        MyApplication.widthPixels = dm.widthPixels;
        MyApplication.heightPixels = dm.heightPixels;
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    public void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }


    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView();


    public abstract void initListener();

    /**
     * 初始数据
     */
    public abstract void initdata();


    @Override
    public void showMsg(String msg) {
        if (msg == null)
            return;
        showTip(msg);
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoading() {
        LoadingUtil.showLoading(this);
    }

    @Override
    public void dissmissLoading() {
        LoadingUtil.hideLoading();
    }

    @Override
    public void updateLoading() {

    }

    //判断页面是否被销毁
    public boolean isFinish() {
        return this.isFinishing();
    }



    public AbstactDailog dialog ;
    @Override
    public void invalidToke() {
        if(dialog == null || !dialog.isShowing()){
            dialog =new AbstactDailog(this);
            dialog.setTitle(getString(R.string.invalidtoke)).
                    setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.showLogin(BaseActivity.this,null);
                            dialog.dismiss();
                        }
                    }, getString(R.string.confim1))
                    .setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    },getString(R.string.cancel))
                    .show();
        }
    }



}
