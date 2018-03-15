package com.qeasy.samrtlockb.listener;


import com.qeasy.samrtlockb.bean.Member;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.listener
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/12
 * <p>
 * ==============================================
 */
public interface LockListener {

    void stop(int pos,Member member);//停用

    void edit(int pos,Member member);//编辑

    void recode(int pos,Member member);//使用记录

    void start(int pos,Member member);//启用

}
