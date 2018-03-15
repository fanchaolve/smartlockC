package com.qeasy.samrtlockb.api;

/**
 * Created by hp on 2016/8/9.
 */
public class Result_Api<T> {




    private String status="";//返回状态

    private String description="";//返回描述

    private String text ="";//返回描述
    private T t;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
