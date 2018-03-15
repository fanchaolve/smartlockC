package com.qeasy.samrtlockb.api;

import android.util.Log;


import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.Constants;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * Created by Administrator on 2016/12/1.
 */

public class BasicParamsInterceptor implements Interceptor {




    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("x-client-key", Constants.CLIENT_KEY);
        requestBuilder.addHeader("x-client-token", AppManager.getInstance().getToken());
        Log.i("fancl","token:"+AppManager.getInstance().getToken());
        request = requestBuilder.build();
        return chain.proceed(request);
    }





}
