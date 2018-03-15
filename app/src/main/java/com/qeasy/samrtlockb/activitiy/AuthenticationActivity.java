package com.qeasy.samrtlockb.activitiy;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hc.facecontrast.AuthApi;
import com.hc.facecontrast.IAuth;
import com.hc.facecontrast.NfcAuth;
import com.hc.facecontrast.NfcAuthApi;
import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.TempPasswordModle;
import com.qeasy.samrtlockb.base.p.TempPasswoldPresenter;
import com.qeasy.samrtlockb.base.v.TemporarypassContract;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.StatusBarUtil;
import com.qeasy.samrtlockb.widget.AbstactDailog;
import com.veritrans.IdReader.HolyReader;
import com.veritrans.IdReader.OnReadCardListener;
import com.veritrans.IdReader.domain.IdCard;

import butterknife.BindView;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.activitiy
 * <p>
 * 说明：身份验证
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/6
 * <p>
 * ==============================================
 */
public class AuthenticationActivity extends BaseActivity
        <TempPasswoldPresenter, TempPasswordModle> implements TemporarypassContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_name)
    TextView title_name;

    @BindView(R.id.title_back)
    ImageView title_back;

    private AuthApi api;

    private boolean isStartOk=false;

    private  boolean isLoding =false;


    @SuppressLint("HandlerLeak")
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HolyReader.RESULT_SUCCESS:
                   showMsg("身份证信息获取成功！");
                    IdCard idCard = (IdCard) msg.obj;
                    if(!isFinish()) {
                        showDailog(idCard);
                    }
                    break;
                case HolyReader.RESULT_CARD_DISCONNECT:
                    showMsg("身份证接触不良！");
                    isLoding =false;
                    break;
                case HolyReader.RESULT_NETWORK_TIMEOUT:
                    showMsg("网络连接超时！");
                    isLoding =false;
                    break;
                default:
                    showMsg("未知错误！");
                    isLoding =false;
                    break;
            }
        }
    };



    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initView() {
        StatusBarUtil.initStatusBar(this, toolbar, false);
        title_name.setText(getString(R.string.authentication3));
        title_back.setVisibility(View.VISIBLE);
        title_back.setImageResource(R.mipmap.icon_fanhui);
    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey(Constants.SERINO)) {
                //serialNo = bundle.getString(Constants.SERINO);
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        api =new NfcAuthApi();
        isStartOk = api.initApi(this,new Intent(this, getClass()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isStartOk)
            api.initApi2(this);
    }

    @Override
    public void changeInfoSucess() {
        showMsg("修改成功");
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(api instanceof  NfcAuthApi) {
            if (intent != null && "android.nfc.action.TECH_DISCOVERED"
            .equalsIgnoreCase(intent.getAction())) {
                if(isLoding)
                    return;
                api.onstartApi(intent, new OnReadCardListener() {
                    @Override
                    public void onFinish(int i, final IdCard idCard) {
                        Message message = mHandler.obtainMessage(i, idCard);
                        message.arg1 = i;
                        mHandler.sendMessage(message);

                    }
                });
            }
        }

    }

    private void showDailog(IdCard idCard){
        new AbstactDailog(mContext).setContent("姓名："+idCard.getName()+"\n"+
                "身份证："+idCard.getNumber()).setBitmap(idCard.getPortrait())
                .setmPositiveButtonListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Navigation.showFace(null);
                        dialog.dismiss();
                    }
                }, getString(R.string.confim1)).
                setmNegativeButtonListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isLoding =false;
                        showMsg("重新识别身份证！！！");
                        dialog.dismiss();
                    }
                },getString(R.string.restart)).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        api.disable(this);
    }
}
