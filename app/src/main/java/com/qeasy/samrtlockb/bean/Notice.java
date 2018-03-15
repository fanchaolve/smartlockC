package com.qeasy.samrtlockb.bean;


import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：消息
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/8
 * <p>
 * ==============================================
 */
public class Notice implements Serializable {

    private int id;

    private String title= "";

    private long releaseTime;

    private String userId;

    private int type;

    private String content ="";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
