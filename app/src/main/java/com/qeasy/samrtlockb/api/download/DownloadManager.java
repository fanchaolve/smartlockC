package com.qeasy.samrtlockb.api.download;


import android.content.Context;

import android.support.annotation.NonNull;


import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.utils.IntentUtils;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


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

public class DownloadManager {

    private static int lastProgress = 0;


    public static void downloadAPK(final Context context, @NonNull String url, final VersionParams versionParams, final DownloadListener listener) {
        if (url == null || url.isEmpty()) {
            return;
        }

        String downloadPath = versionParams.getDownloadAPKPath() + context.getString(R.string.app_name);
        checkAPKIsExists(downloadPath);
        lastProgress = 0;
        Request request = new Request.Builder().url(url).build();
        MyHttp.getHttpClient().newCall(request).enqueue(new FileCallBack(
                versionParams.getDownloadAPKPath(),
                context.getString(R.string.app_name)
        ) {

            @Override
            public void onDownloading(int progress) {
                int currentProgress = progress;
                listener.onCheckerDownloading(currentProgress);

            }

            @Override
            public void onDownloadFailed() {
                listener.onCheckerDownloadFail();
            }

            @Override
            public void onSuccess(File file, Call call, Response response) {
                listener.onCheckerDownloadSuccess(file);
                IntentUtils.installApk(context, file);

            }
        });


    }


    public static void checkAPKIsExists(String downloadPath) {
        File file = new File(downloadPath);
        if (file.exists()) {
            file.delete();

        }
    }
}
