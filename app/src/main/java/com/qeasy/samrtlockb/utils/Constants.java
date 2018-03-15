package com.qeasy.samrtlockb.utils;

import com.qeasy.samrtlockb.api.GenApiHashUrl;

/**
 * Created by Administrator on 2016/12/22.
 */

public final class Constants {





    //接口配置
    public static final String APPID = "BB17ACCE051861385B5BF6ACB6EAB7C8";
    public static final String SINKEY = "DFFEFB04B85D3F3CD04EC5B821922823";


    public static final String CODE_00 = "00";//成功



    // 智能锁
    public static final String CLIENT_KEY = "x-android-customer lU9ZCkpxiL9PW86QevPXhs";

    //服务器返回结果的状态

    //成功
    public  static  final  String SUCCESS="SUCCESS";


    //token过期
    public  static  final  String SESSION_EXPIRY="SESSION_EXPIRY";


    //锁的状态
    public static final  int IDLE =10;//空闲
    public static  final  int USING = 20;//使用中
    public  static  final  int UNUSUAL = 30;//异常
    public  static  final  int STOP = 90;//异常

    //下啦刷新和上蜡加载
    public  static  final  int FRESH= 1 ;

    public static final  int LOADMORE =  0;



    //传递
    public  static  final  String SERINO="serialNo";
    public  static  final  String TITLE="title";
    public  static  final  String ADDR="address";
    public  static  final  String MEMBER="member";










}
