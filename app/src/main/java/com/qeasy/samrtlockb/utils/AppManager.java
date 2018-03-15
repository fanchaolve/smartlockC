package com.qeasy.samrtlockb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.qeasy.samrtlockb.MyApplication;
import com.qeasy.samrtlockb.bean.User;
import com.qeasy.samrtlockb.db.DatabaseImpl;

import java.util.Stack;

/**
 * Created by Administrator on 2016/11/25.
 */

public class AppManager {

    private static Stack<Activity>
            activityStack;

    private static AppManager instance;

    public static String QUTO = "";


    private static final String DEFAULT_DATA_BASEPATH = "/bnh"; // 缓存目录
    public String DEFAULT_DATA_IMAGEPATH = DEFAULT_DATA_BASEPATH + "/IMAGE"; // 小图缓存地址
    public String DEFAULT_DATA_TEMP = DEFAULT_DATA_BASEPATH + "/TEMP"; // 备份数据地址
    public String DEFAULT_DATA_BIG_IMAGEPATH = DEFAULT_DATA_BASEPATH + "/BIGIMAGE"; // 大图缓存地址

    private AppManager() {
        String rootPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        DEFAULT_DATA_IMAGEPATH = rootPath + DEFAULT_DATA_IMAGEPATH;
        DEFAULT_DATA_TEMP = rootPath + DEFAULT_DATA_TEMP;
        DEFAULT_DATA_BIG_IMAGEPATH = rootPath + DEFAULT_DATA_BIG_IMAGEPATH;
    }


    //获取用户信息
    public User getUser() {
        if (MyApplication.user != null)
            return MyApplication.user;
        else {
            User user = DatabaseImpl.getInstance().getUser();
            MyApplication.user = user;
            return user;
        }
    }

    //添加用户信息
    public void addUser(User user) {
        MyApplication.user = user;
        DatabaseImpl.getInstance().addUser(user);
    }

    //删除用户信息
    public void deleteUser() {
        MyApplication.user = null;
        DatabaseImpl.getInstance().removeUser();
    }




    /**
     * 单一实例
     */

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    //获取会员id
    public String getMerchantId() {
        if (getUser() != null) {
            return getUser().getMemberId();
        } else
            return "";
    }

    public String getToken(){
        if (getUser() != null) {
            return getUser().getAccessToken();
        } else
            return "";
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);

    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack.size() > 0) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = currentActivity();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            //activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishParticularActivity(Class<?> tClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(tClass)) {
                if (activity != null) {
                    activity.finish();

                }
            }
        }


    }


    /**
     * 指定的act
     *
     * @param tClass
     * @return
     */
    public Activity getAppActivity(Class<?> tClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(tClass)) {
                return activity;
            }
        }
        return null;
    }


    /**
     * 结束指定类名的Activity
     */

    public void finishActivity(Class<?> tClass) {

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(tClass)) {
                finishActivity(activity);
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = size - 1; i >= 0; i--) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
                activityStack.remove(i);
            }
        }
        activityStack.clear();
    }


    public boolean processBackKey(Class<? extends Activity>... classes) {
        for (Class<? extends Activity> clazz : classes) {
            if (currentActivity().getClass().getSimpleName().equals(clazz.getSimpleName())) {
                //进入最底层的页面 需要做具体操作来结束app页面，入弹出dialog
                // TODO: 2016/11/25
                return true;
            }
        }
        return false;
    }


    public void AppExit(Context context) {
        finishAllActivity();
//        ActivityManager activityManager=context.getSystemService(Context.ACTIVITY_SERVICE);
        // android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }


    /**
     * @param goActivity 跳转的页面
     * @param aBundle    带参数
     */
    public void showActivity(Class<? extends Activity> goActivity, Bundle aBundle) {
        //跳转页面
        Activity activity = currentActivity();
        if(activity == null)
            return;
        Intent intent = new Intent();
        intent.setClassName(activity, goActivity.getName());
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
        activity.startActivity(intent);
    }


    /**
     * @param goActivity  跳转的页面
     * @param aBundle     带参数
     * @param requestCode
     */
    public void showActivityForResult(Class<? extends Activity> goActivity, Bundle aBundle, int requestCode) {
        Intent intent = new Intent();
        Activity activity = currentActivity();
        intent.setClassName(activity, goActivity.getName());
        if (null != aBundle) {
            intent.putExtras(aBundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }


//    /**
//     * token失效
//     */
//    public void invalidToke() {
//        final Activity activity =currentActivity();
//        if (activity == null)
//            return;
//
//
//    }
}
