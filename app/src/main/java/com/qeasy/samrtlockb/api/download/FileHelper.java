package com.qeasy.samrtlockb.api.download;

import com.qeasy.samrtlockb.utils.AppManager;
import com.qeasy.samrtlockb.utils.file.FileUtils;

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
 * 时间：2017/12/14
 * <p>
 * ==============================================
 */

public class FileHelper {

    public static String getDownloadApkCachePath() {

        String appCachePath = null;
        if (FileUtils.checkSDCard()) {
            appCachePath = AppManager.getInstance().DEFAULT_DATA_TEMP;
            File file = new File(appCachePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        return appCachePath;
    }
}
