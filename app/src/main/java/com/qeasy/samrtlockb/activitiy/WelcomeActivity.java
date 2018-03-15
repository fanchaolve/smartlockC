package com.qeasy.samrtlockb.activitiy;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.qeasy.samrtlockb.Navigation;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.activitiy.login.LoginActivity;
import com.qeasy.samrtlockb.base.BaseActivity;
import com.qeasy.samrtlockb.base.m.WelcomeModle;
import com.qeasy.samrtlockb.base.p.WelcomePresenter;
import com.qeasy.samrtlockb.base.v.WelcomeContract;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.PermissionUtil;

public class WelcomeActivity extends BaseActivity<WelcomePresenter, WelcomeModle>
        implements WelcomeContract.View {


    private PermissionUtil permissionUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }


    @Override
    public void initdata() {
        queryPermiss();
    }


    private void queryPermiss() {

        PermissionUtil.onPermissionGentedListener listener = new PermissionUtil.onPermissionGentedListener() {
            @Override
            public void onGented() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(isFinish())
                            return;
                        if(AppManager.getInstance().getUser() == null)
                            AppManager.getInstance().showActivity(LoginActivity.class, null);
                        else
                            AppManager.getInstance().showActivity(HomeActivity.class, null);

                    }
                }, 3000);

            }

            @Override
            public void onFalied() {
                finish();
            }
        };
        permissionUtil = PermissionUtil.getInstance();
        permissionUtil.setListener(listener);
        permissionUtil.cameraTask();


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }





}
