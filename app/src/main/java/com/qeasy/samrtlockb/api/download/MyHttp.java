package com.qeasy.samrtlockb.api.download;

import okhttp3.OkHttpClient;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.api.download
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/13
 * <p>
 * ==============================================
 */

public class MyHttp {
    
    private static OkHttpClient client;

    public static OkHttpClient getHttpClient() {
        if (client == null)
            client = new OkHttpClient();
        return client;
    }
}
