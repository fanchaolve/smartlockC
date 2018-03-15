package com.qeasy.samrtlockb.bean;


import java.util.List;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/8
 * <p>
 * ==============================================
 */
public class NoticeResult {

    private List<Notice>rows;

    private int total;

    public List<Notice> getRows() {
        return rows;
    }

    public void setRows(List<Notice> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
