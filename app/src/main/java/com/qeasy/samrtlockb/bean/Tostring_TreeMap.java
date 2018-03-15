package com.qeasy.samrtlockb.bean;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * ==============================================
 * <p>
 * 包名：
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/7
 * <p>
 * ==============================================
 */

public class Tostring_TreeMap<K,V>  extends TreeMap{

    public Tostring_TreeMap(Comparator<K> comparator) {
        super(comparator);
    }


    //加签用
    public String sign(){
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();

        while (i.hasNext()){
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            if(!TextUtils.isEmpty(String.valueOf(value))){
                sb.append(key   == this ? "(this Map)" : key);
                sb.append('=');
                sb.append(value == this ? "(this Map)" : value);
                sb.append('&');
            }
        }
        return sb.toString();

    }

    public String toString() {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();

        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            try {
                sb.append(value == this ? "(this Map)" : URLEncoder.encode((String) value,"utf-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            if (! i.hasNext())
                return sb.toString();
            sb.append('&');
        }
    }

}
