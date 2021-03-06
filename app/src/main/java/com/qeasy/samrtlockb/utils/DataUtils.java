package com.qeasy.samrtlockb.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ==============================================
 * <p>
 * 包名：
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/14
 * <p>
 * ==============================================
 */

public class DataUtils {

    public static Map<String, String> getMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("初中及以下", "10");
        map.put("专科", "20");
        map.put("高中", "30");
        map.put("本科", "40");
        map.put("硕士", "50");
        map.put("博士及以上", "60");
        return map;
    }

    public static String getEduText(int edu) {
        String des = "未知";
        switch (edu) {
            case 10:
                des = "初中及以下";
                break;
            case 20:
                des = "专科";
                break;
            case 30:
                des = "高中";
                break;
            case 40:
                des = "本科";
                break;
            case 50:
                des = "硕士";
                break;
            case 60:
                des = "博士及以上";
                break;

        }
        return des;

    }


    public static Map<String, String> getShipsMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("父母", "10");
        map.put("配偶", "20");
        map.put("子女", "40");
        map.put("兄弟姐妹", "30");
        return map;
    }


    public static Map<String, String> fristShipsMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("父亲", "10");
        map.put("母亲", "20");
        map.put("配偶", "30");
        map.put("子女", "40");
        map.put("其他亲属", "50");
        return map;
    }

    public static String getShipText(int code) {
        String des = "未知";
        switch (code) {
            case 10:
                des = "父亲";
                break;
            case 20:
                des = "母亲";
                break;
            case 30:
                des = "配偶";
                break;
            case 40:
                des = "子女";
                break;
            case 50:
                des = "其他亲属";
                break;
            case 60:
                des = "朋友";
                break;
            case 70:
                des = "同事";
                break;
            case 80:
                des = "其他";
                break;
        }
        return des;
    }

    public static List<String> getP1() {
         ArrayList<String> p1 = new ArrayList<>();
         for(int i = 1; i<30; i++){
             p1.add(i+"");
         }
         return p1;
    }

    public static List<String> getP2() {
        ArrayList<String> p2= new ArrayList<>();
        p2.add("天");
        p2.add("周");
        p2.add("月");
        return p2;
    }

    public static List<String> getP3() {
        ArrayList<String> p3= new ArrayList<>();
        p3.add("1次");

        return p3;
    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


}
