package com.hc.facecontrast;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcB;
import android.widget.Toast;

import com.veritrans.IdReader.HolyReader;
import com.veritrans.IdReader.OnReadCardListener;

/**
 * ==============================================
 * <p>
 * 包名：com.hc.facecontrast
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/11
 * <p>
 * ==============================================
 */
public abstract class AuthApi {

    public PendingIntent mNfcPendingIntent;
    public IntentFilter mTagDetectedIntentFilter;
    public String[][] mTechLists;

    public HolyReader mHolyReader;



    public abstract  boolean initApi(Activity mContext,Intent intent);

    public abstract  void initApi2(Activity mContext);

    public abstract  void onstartApi(Intent tagIntent, OnReadCardListener mOnReadCardListener);

    public  abstract void disable(Activity mContext);

}
