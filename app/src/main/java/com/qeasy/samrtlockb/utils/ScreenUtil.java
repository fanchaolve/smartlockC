package com.qeasy.samrtlockb.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;


/**
 * 屏幕尺寸获取工具
 * Created by wangdong on 2017/9/12.
 */

public class ScreenUtil {
    private static int ScrrenWidth = 0;
    private static int ScreenHeight = 0;

    private static void init() {
        if (ScrrenWidth == 0 || ScreenHeight == 0) {
            DisplayMetrics metrics = new DisplayMetrics();
            Activity activity = AppManager.getInstance().currentActivity();
            if (activity != null) {
                WindowManager manager = activity.getWindowManager();
                manager.getDefaultDisplay().getMetrics(metrics);
                ScrrenWidth = metrics.widthPixels;
                ScreenHeight = metrics.heightPixels;
            }

        }
    }

    public static int getScrrenWidth() {
        init();
        return ScrrenWidth;
    }

    public static int getScreenHeight() {
        init();
        return ScreenHeight;
    }
}
