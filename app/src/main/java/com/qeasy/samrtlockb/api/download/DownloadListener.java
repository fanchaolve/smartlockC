package com.qeasy.samrtlockb.api.download;

import java.io.File;

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

public interface DownloadListener {
    void onCheckerDownloading(int progress);
    void onCheckerDownloadSuccess(File file);
    void onCheckerDownloadFail();
}
