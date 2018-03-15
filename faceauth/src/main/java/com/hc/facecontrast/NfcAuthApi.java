package com.hc.facecontrast;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcB;
import android.util.Log;
import android.widget.Toast;

import com.veritrans.IdReader.HolyReader;
import com.veritrans.IdReader.OnReadCardListener;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public  class NfcAuthApi extends AuthApi{


    private NfcAdapter mNfcAdapter;
    public   boolean initApi(Activity mContext,Intent intent){
        mNfcAdapter = NfcAdapter.getDefaultAdapter(mContext);
        if (mNfcAdapter == null) {
            Toast.makeText(mContext,"该设备没有NFC!",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!mNfcAdapter.isEnabled()) {
            Toast.makeText(mContext,"请先开启NFC!",Toast.LENGTH_LONG).show();
            return false;
        }
        //一旦截获NFC消息，就会通过PendingIntent调用窗口
        mNfcPendingIntent = PendingIntent.getActivity(mContext, 0, intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mTagDetectedIntentFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);//.ACTION_TAG_DISCOVERED);
        mTagDetectedIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        mTechLists = new String[][]{new String[]{NfcB.class.getName()}};
        mHolyReader = new HolyReader(mContext, DataUtils.DEFAULT_IP, DataUtils.DEFAULT_PORT);
        mHolyReader.SetAppidKey(DataUtils.APPID, DataUtils.APPKEY);
        mHolyReader.SetFaceInfo(DataUtils.DEFAULT_FACE_IP, DataUtils.DEFAULT_FACE_PORT);
        return  true;
    }

    @Override
    public void initApi2(Activity mContext) {
        mNfcAdapter.enableForegroundDispatch(mContext, mNfcPendingIntent, new IntentFilter[]{mTagDetectedIntentFilter}, mTechLists);
    }

    @Override
    public void onstartApi(Intent tagIntent, OnReadCardListener mOnReadCardListener) {
        // 在此暂时以时间为一个流水号
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mTransctionId = sdf.format(date.getTime());
        if (null != mHolyReader) {
            try {
                mHolyReader.nfcReadCard(mTransctionId, tagIntent, mOnReadCardListener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void disable(Activity mContext) {
        if (null != mNfcAdapter && mNfcAdapter.isEnabled()) {
            //恢复默认状态
            mNfcAdapter.disableForegroundDispatch(mContext);
        }
    }


}
