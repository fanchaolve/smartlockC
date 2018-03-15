package com.qeasy.samrtlockb.bean;


import java.io.Serializable;
import java.util.List;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.bean
 * <p>
 * 说明：添加人员
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/9
 * <p>
 * ==============================================
 */
public class MemberResult implements Serializable {




    private List<Member>rows;

    private int total;

    public List<Member> getRows() {
        return rows;
    }

    public void setRows(List<Member> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
