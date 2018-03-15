package com.qeasy.samrtlockb.bean;


import java.io.Serializable;
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
 * 时间：2018/3/7
 * <p>
 * ==============================================
 */
public class SmartlockRecordResult implements Serializable{


   private List<SmartlockRecord> rows ;

   private int total ;

    public List<SmartlockRecord> getRows() {
        return rows;
    }

    public void setRows(List<SmartlockRecord> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
