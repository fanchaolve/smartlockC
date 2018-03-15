package com.qeasy.samrtlockb;

import android.app.Activity;
import android.os.Bundle;

import com.google.zxing.client.android.CaptureActivity;
import com.qeasy.samrtlockb.activitiy.AddMemberActivity;
import com.qeasy.samrtlockb.activitiy.AuthenticationActivity;
import com.qeasy.samrtlockb.activitiy.BindingSl1Activity;
import com.qeasy.samrtlockb.activitiy.FaceAuthActivity;
import com.qeasy.samrtlockb.activitiy.FigerprintingActivity;
import com.qeasy.samrtlockb.activitiy.FingerprintActivity;
import com.qeasy.samrtlockb.activitiy.HomeActivity;
import com.qeasy.samrtlockb.activitiy.ICSettingActivity;
import com.qeasy.samrtlockb.activitiy.IcSmartLockActivity;
import com.qeasy.samrtlockb.activitiy.NoticeActivity;
import com.qeasy.samrtlockb.activitiy.SettingActivity;
import com.qeasy.samrtlockb.activitiy.TemporarypasswordActivity;
import com.qeasy.samrtlockb.activitiy.UnnormalActivity;
import com.qeasy.samrtlockb.activitiy.login.LoginActivity;
import com.qeasy.samrtlockb.utils.AppManager;


/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/11/14
 * <p>
 * ==============================================
 */

public class Navigation {


    //登录跳转
    public static void showLogin(Activity activity, Bundle bundle) {
        AppManager.getInstance().showActivity(LoginActivity.class, bundle);

    }




    //设置
    public static void showSetting(Bundle bundle) {
        AppManager.getInstance().showActivity(SettingActivity.class, bundle);

    }

    //通知
    public static void showNotice(Bundle bundle) {
        AppManager.getInstance().showActivity(NoticeActivity.class, bundle);

    }

    //通知
    public static void showChangePassword(Bundle bundle) {
        AppManager.getInstance().showActivity(TemporarypasswordActivity.class, bundle);

    }


    //ic设置
    public static void showicSett(Bundle bundle) {
        AppManager.getInstance().showActivity(IcSmartLockActivity.class, bundle);


    }


    //ic配置ing
    public static void showicSetting(Bundle bundle,int requestcode) {
        AppManager.getInstance().showActivityForResult(ICSettingActivity.class, bundle,requestcode);

    }

    //指纹设置
    public static void showFigerp(Bundle bundle) {
        AppManager.getInstance().showActivity(FingerprintActivity.class, bundle);


    }


    //指纹配置ing
    public static void showFigerping(Bundle bundle,int requestcode) {
        AppManager.getInstance().showActivityForResult(FigerprintingActivity.class, bundle,requestcode);

    }




    //添加人员
    public static void showAddmember(Bundle bundle) {
        AppManager.getInstance().showActivity(AddMemberActivity.class, bundle);
    }

    //添加人员2
    public static void showAddmember2(Bundle bundle,int requestCode) {
        AppManager.getInstance().showActivityForResult(AddMemberActivity.class, bundle,requestCode);
    }

    //身份验证
    public static void showAuthentication(Bundle bundle) {
        AppManager.getInstance().showActivity(AuthenticationActivity.class, bundle);
    }

    //活体
    public static void showFace(Bundle bundle) {
        AppManager.getInstance().showActivity(FaceAuthActivity.class, bundle);
    }




    //---------------C端
    //未绑定锁之前的显示页面
    public static void showbinding1(Bundle bundle) {
        AppManager.getInstance().showActivity(BindingSl1Activity.class, bundle);
    }

    //扫一扫
    public static void showCapture(Bundle bundle,int requestcode) {
        AppManager.getInstance().showActivityForResult(CaptureActivity.class, bundle,requestcode);
    }

    public static void showHome(Bundle bundle){
        AppManager.getInstance().showActivity(HomeActivity.class,null);
    }

    //申报异常
    public static void showUnnormal(Bundle bundle){
        AppManager.getInstance().showActivity(UnnormalActivity.class,bundle);
    }





}
