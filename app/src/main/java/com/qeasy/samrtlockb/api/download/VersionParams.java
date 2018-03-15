package com.qeasy.samrtlockb.api.download;

import android.os.Parcel;
import android.os.Parcelable;

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

public class VersionParams implements Parcelable {


    public boolean isForceUpdate;//是否强制更新
    private String title = "";//标题
    private String updateMsg = "";//说明
    private String downloadUrl = "";//下载地址
    private String downloadAPKPath;//下载的存放地址

    private String other="";

    private VersionParams() {
    }


    protected VersionParams(Parcel in) {
        isForceUpdate = in.readByte() != 0;
        title = in.readString();
        updateMsg = in.readString();
        downloadUrl = in.readString();
        downloadAPKPath = in.readString();
        other =in.readString();
    }

    public static final Creator<VersionParams> CREATOR = new Creator<VersionParams>() {
        @Override
        public VersionParams createFromParcel(Parcel in) {
            return new VersionParams(in);
        }

        @Override
        public VersionParams[] newArray(int size) {
            return new VersionParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isForceUpdate ? 1 : 0));
        dest.writeString(title);
        dest.writeString(updateMsg);
        dest.writeString(downloadUrl);
        dest.writeString(downloadAPKPath);
        dest.writeString(other);
    }


    public static class Builder {
        VersionParams params;

        public Builder() {
            params = new VersionParams();
            params.downloadAPKPath = FileHelper.getDownloadApkCachePath();
            params.isForceUpdate = false;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setUpdateMsg(String updateMsg) {
            params.updateMsg = updateMsg;
            return this;
        }

        public Builder setDownloadUrl(String downloadUrl) {
            params.downloadUrl = downloadUrl;
            return this;
        }

        public Builder setForceUpdate(boolean isForceUpdate){
            params.isForceUpdate =isForceUpdate;
            return this;

        }

        public Builder setOther(String setOther){
            params.other =setOther;
            return this;

        }

        public VersionParams build() {
            return params;
        }
    }

    public boolean isForceUpdate() {
        return isForceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        isForceUpdate = forceUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadAPKPath() {
        return downloadAPKPath;
    }

    public void setDownloadAPKPath(String downloadAPKPath) {
        this.downloadAPKPath = downloadAPKPath;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
