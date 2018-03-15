package com.qeasy.samrtlockb;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.qeasy.samrtlockb.bean.User;

import com.qeasy.samrtlockb.utils.gps.GPSUtil;

import cn.jpush.android.api.JPushInterface;



/**
 * Created by Administrator on 2016/12/6.
 */

public class MyApplication extends Application {


    private static MyApplication INSTANCE = null;
    private static Context context;

    public static String latitude = "";// 纬度

    public static String longitude = "";//经度

    public static String city;//城市


    public static String DUID = "";// 设备唯一码
    public static int widthPixels = 321;// 屏幕宽度
    public static int heightPixels = 481;//屏幕高度

    public static User user;// 用户信息

    public boolean start_up =false;






    public static final Context getAppContext() {
        return context;
    }


    public static MyApplication getInstance() {
        return INSTANCE;
    }


    public boolean isStart_up() {
        return start_up;
    }

    public void setStart_up(boolean start_up) {
        this.start_up = start_up;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        INSTANCE = this;
        GPSUtil.tryInit(this);//定位

        if(BuildConfig.DEBUG) {// 设置开启日志,发布时请关闭日志
            JPushInterface.setDebugMode(true);
        }else {
            JPushInterface.setDebugMode(false);

        }

        JPushInterface.init(this);     		// 初始化 JPush
        JPushInterface.initCrashHandler(this);





        //添加崩溃统一处理
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }


}
