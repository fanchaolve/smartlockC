package com.qeasy.samrtlockb.utils;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.utils
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/1/9
 * <p>
 * ==============================================
 */

public class MobclickAgentUtils {



    //注册事件
    public static void MobclickAgent_register(Activity activity){
        MobclickAgent.onEvent(activity, "register");
    }

    //登陆事件
    public static void MobclickAgent_logged(Activity activity){
        MobclickAgent.onEvent(activity, "logged");
    }

    //未登陆事件
    public static void MobclickAgent_not_logged_in(Activity activity){
        MobclickAgent.onEvent(activity, "not_logged_in");
    }

    //联系人事件
    public static void MobclickAgent_contact_message(Activity activity){
        MobclickAgent.onEvent(activity, "contact_message");
    }

    //添加银行卡事件
    public static void MobclickAgent_add_bankcard(Activity activity){
        MobclickAgent.onEvent(activity, "add_bankcard");
    }

    //确认还款事件
    public static void MobclickAgent_confirm_repayment(Activity activity){
        MobclickAgent.onEvent(activity, "confirm_repayment");
    }

    //实名认证事件
    public static void MobclickAgent_certification(Activity activity){
        MobclickAgent.onEvent(activity, "certification");
    }




}
