package com.qeasy.samrtlockb.activitiy;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.HomeModle;
import com.qeasy.samrtlockb.base.p.HomePresenter;
import com.qeasy.samrtlockb.base.v.HomeContract;
import com.qeasy.samrtlockb.bean.SmarLock;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.IntentUtils;
import com.qeasy.samrtlockb.utils.LojaDateUtils;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.AbstactDailog;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter, HomeModle> implements HomeContract.View {



    @BindView(R.id.lin_title)
    LinearLayout lin_title;

    @BindView(R.id.iv_setting)
    ImageView iv_setting;

    @BindView(R.id.tv_abnormal)
    TextView tv_abnormal;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_timetotime)
    TextView tv_timetotime;

    @BindView(R.id.tv_jiedian)
    TextView tv_jiedian;

    @BindView(R.id.tv_password)
    TextView tv_password;

    @BindView(R.id.rel_password)
    RelativeLayout rel_password;

    @BindView(R.id.rel_fingerprint)
    RelativeLayout rel_fingerprint;

    @BindView(R.id.rel_icS)
    RelativeLayout rel_icS;


    @BindView(R.id.iv_zw)
    ImageView iv_zw;

    @BindView(R.id.iv_ics)
    ImageView iv_ics;

    @BindView(R.id.iv_tel)
    ImageView iv_tel;

    @BindView(R.id.iv_notice)
    ImageView iv_notice;


    @BindView(R.id.tv_serialNo)
    TextView tv_serialNo;



    @BindView(R.id.iv_saoyisao)
    ImageView iv_saoyisao;



    SmarLock smarLock;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home2;
    }

    @Override
    public void initView() {
 //       setSupportActionBar(toolbar);
//        title_name.setText(getResources().getString(R.string.home_title));
//        title_back.setVisibility(View.VISIBLE);
//        title_back.setImageResource(R.mipmap.icon_saoyisao);
//        iv_right.setVisibility(View.VISIBLE);
//        iv_right.setImageResource(R.mipmap.icon_xiaoxi);

        StatusBarUtil.initStatusBar(this, lin_title, false);


    }


    @Override
    public void initListener() {


        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle =new Bundle();
                //bundle.putString();
                Navigation.showSetting(null);
            }
        });

        tv_abnormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smarLock==null || TextUtils.isEmpty(smarLock.getSerialNo()))
                    return;
                Bundle bundle =new Bundle();
                bundle.putString(Constants.SERINO,smarLock.getSerialNo());
                Navigation.showUnnormal(bundle);
            }
        });

        iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.tel();
            }
        });

        iv_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showNotice(null);
            }
        });


        iv_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.showCapture(null, 0);
            }
        });

    }


    @Override
    public void initdata() {
        mPresenter.homeData();
        Log.i("fancl","initdata");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mPresenter.homeData();
        Log.i("fancl","onNewIntent");


    }

    //字体颜色反转
    private void changeTextStyle(ViewGroup group,boolean isUse){
       for(int i = 0 ; group != null && i < group.getChildCount(); i++ ){
           View view =group.getChildAt(i);
           if(view instanceof  TextView){
               if(isUse)
                   ((TextView) view).setTextColor(getResources().getColor(R.color.C19));
               else
                   ((TextView) view).setTextColor(getResources().getColor(R.color.C18));
           }
       }
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


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void getHomeData(SmarLock smarLock) {
        this.smarLock = smarLock;
        tv_title.setText(smarLock.getShortName());
        tv_timetotime.setText(getString(R.string.timetotime,
                LojaDateUtils.format(smarLock.getUseStartTime(),LojaDateUtils.YYYY_MM_DD_FORMAT),
                LojaDateUtils.format(smarLock.getUseEndTime(),LojaDateUtils.YYYY_MM_DD_FORMAT)));
        tv_password.setText(smarLock.getPinCode());
        tv_serialNo.setText(getString(R.string.serino,smarLock.getSerialNo()));
        if(smarLock.isOverdue()){
            changeViewStyle(false);
            //iv_zw.setImageResource();
            //iv_ics.setImageResource();
            return;
        }


        if(smarLock.getIsPinCode() ==10){
            changeTextStyle(rel_password,true);
        }else {
            changeTextStyle(rel_password,false);
        }


        if(smarLock.getIsFingerprintCode() ==10){
            changeTextStyle(rel_fingerprint,true);
        }else {
            changeTextStyle(rel_fingerprint,false);
        }

        if(smarLock.getIsFingerprintCode() ==10){
            changeTextStyle(rel_icS,true);
        }else {
            changeTextStyle(rel_icS,false);
        }


    }

    @Override
    public void getNoneData() {
        Navigation.showbinding1(null);
    }

    @Override
    public void changeViewStyle(boolean isUse) {
        changeTextStyle(rel_password,isUse);
        changeTextStyle(rel_fingerprint,isUse);
        changeTextStyle(rel_icS,isUse);


    }

    @Override
    public void getTel(final String tel) {
        new AbstactDailog(this).setContent("确认拨打客服电话："+tel)
                .setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IntentUtils.dialPhoneNumber(HomeActivity.this, tel);
                        dialog.dismiss();
                    }
                },getString(R.string.confim1))
                .setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },getString(R.string.cancel)).show();
    }
}
