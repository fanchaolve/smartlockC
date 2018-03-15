package com.qeasy.samrtlockb.bean;


import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：异常类
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/14
 * <p>
 * ==============================================
 */
public class Unnormal implements Serializable{

    private String content ="";

    private boolean isSelect ;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
