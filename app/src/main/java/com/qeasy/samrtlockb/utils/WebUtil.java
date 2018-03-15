package com.qeasy.samrtlockb.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.qeasy.samrtlockb.MyApplication;


/**
 *
 */

public class WebUtil {

    private static WebUtil instance;

    private WebUtil(){

    }

    public synchronized static WebUtil getInstance(){
        if (instance == null){
            instance = new WebUtil();
        }
        return instance;
    }

    public void initParams(Context context,WebView webView){
        initCookieManager(webView);
        adapter(context,webView);
    }

    private void initCookieManager(WebView webView) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(MyApplication.getAppContext());
        }

        cookieManager.setAcceptCookie(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
    }

    private void adapter(Context context,WebView webView){
        String cacheDir = MyApplication.getInstance().getCacheDir().getAbsolutePath();
        String dbDir = MyApplication.getInstance().getDir("database", Context.MODE_PRIVATE).getPath();

        WebSettings wset = webView.getSettings();

//        wset.setTextZoom(H5Setting.txtZoom());
        wset.setAllowFileAccess(true);
        wset.setAllowContentAccess(true);

        if (Build.VERSION.SDK_INT >= 16) {
            wset.setAllowFileAccessFromFileURLs(true);
            wset.setAllowUniversalAccessFromFileURLs(true);
        }

        wset.setBlockNetworkImage(false); // 解决图片不显示
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wset.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        wset.setAppCacheEnabled(true);

        wset.setAppCachePath(cacheDir);
        wset.setDatabaseEnabled(true);
        wset.setDomStorageEnabled(true);

        wset.setSavePassword(true);
        wset.setSaveFormData(true);

        //调整渲染优化级
//        wset.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        wset.setBlockNetworkImage(true);

        wset.setBuiltInZoomControls(true);

        wset.setGeolocationDatabasePath(dbDir);
        wset.setGeolocationEnabled(true);
        wset.setSupportZoom(true);
        wset.setUseWideViewPort(true);
        wset.setJavaScriptEnabled(true);


        wset.setSupportMultipleWindows(true);
        wset.setJavaScriptCanOpenWindowsAutomatically(true);


        //自适应屏幕
        wset.setLoadWithOverviewMode(true);


        //不显示webview缩放按钮
        wset.setDisplayZoomControls(false);

        //支持获取手势焦点

        wset.setCacheMode(WebSettings.LOAD_DEFAULT);

        wset.setLoadsImagesAutomatically(true);
        webView.setDownloadListener(new MyWebViewDownLoadListener(context,webView));
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        private Context context;
        private WebView webView;

        public MyWebViewDownLoadListener(Context context,WebView webView){
            this.context = context;
            this.webView = webView;
        }

        @Override

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,

                                    long contentLength) {

            Log.i("tag", "url="+url);

            Log.i("tag", "userAgent="+userAgent);

            Log.i("tag", "contentDisposition="+contentDisposition);

            Log.i("tag", "mimetype="+mimetype);

            Log.i("tag", "contentLength="+contentLength);

            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            context.startActivity(intent);

            webView.goBack();

        }

    }
}
