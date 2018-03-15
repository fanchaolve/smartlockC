package com.qeasy.samrtlockb.api;

import android.text.TextUtils;
import android.util.Log;

import com.qeasy.samrtlockb.BuildConfig;
import com.qeasy.samrtlockb.MyApplication;
import com.qeasy.samrtlockb.bean.Tostring_TreeMap;
import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;
import com.qeasy.samrtlockb.utils.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.api
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/18
 * <p>
 * ==============================================
 */

public class UrlParamUtils {
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    private Tostring_TreeMap<String, Object> params;

    private Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    public UrlParamUtils() {
        //排序 升序
        params = new Tostring_TreeMap<>(comparator);
        params.put("appid", Constants.APPID);
        params.put("token", AppManager.getInstance().getToken());
        params.put("version", "1.0");
        params.put("timestamp", System.currentTimeMillis() + "");



    }

    //apiservice 取下参数拼接的method
    private String[] getHeadParam(Request request) {
        HttpUrl url = request.url();
        String url_param = url.encodedQuery();
        String[] key_value = splist(url_param, "=");//method添加
        if (key_value != null)
            return key_value;
        else
            return new String[]{};

    }


    //post请求参数封装
    public Request post_method(Request request) {

        String head[] = getHeadParam(request);
        if (head.length == 2)
            params.put(head[0], head[1]);//切出method 添加到map

        putAppParams(request);
        params.put("sign", sign());
        Request.Builder requestBuilder = request.newBuilder();
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params.toString().getBytes());
        if(BuildConfig.DEBUG)
            Log.i("fancl", "请求内容:" + params.toString());
        requestBuilder.url(GenApiHashUrl.apiUrl + request.url().encodedPath());
        requestBuilder.method(request.method(), body);
        return requestBuilder.build();
    }

    private String sign() {
        StringBuilder builder = new StringBuilder();
        builder.append(params.sign()).append("signKey=" + Constants.SINKEY);
        String signVale = MD5Util.MD5(builder.toString());
        return signVale;
    }


    //切割
    private String[] splist(String value, String key) {
        if (value != null && key != null && value.indexOf(key) > -1) {
            return value.split(key);
        }
        return new String[]{};
    }


    //放入应用级参数
    private void putAppParams(Request request) {
        //获取参数

        try {
            if (request.body() != null) {//formencode
                if (request.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) request.body();
                    for (int i = 0; i < formBody.size(); i++) {//post filed字段添加到map
                        params.put(formBody.name(i), formBody.value(i));
                    }
                } else if (request.body() instanceof RequestBody) {

                    String param_ = bodyToString(request.body());

                    if (!TextUtils.isEmpty(param_)) {
                        JSONObject jsonObject = new JSONObject(param_);
                        if (jsonObject != null && jsonObject.length() > 0) {
                            Iterator<String> iterator = jsonObject.keys();
                            while (iterator.hasNext()) {
                                String key = iterator.next();
                                params.put(key, jsonObject.get(key));
                            }
                        }
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("fancl", e.getMessage());
        }
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
