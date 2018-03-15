package com.qeasy.samrtlockb.adapter;


import android.content.Context;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.recycleradapter.ListBaseAdapter;
import com.qeasy.samrtlockb.adapter.recycleradapter.SuperViewHolder;
import com.qeasy.samrtlockb.bean.SmartlockRecord;
import com.qeasy.samrtlockb.utils.LojaDateUtils;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.adapter
 * <p>
 * 说明：锁详情对应的开锁记录适配器
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/7
 * <p>
 * ==============================================
 */
public class SmartlockRecodeAdapter extends ListBaseAdapter<SmartlockRecord> {


    public SmartlockRecodeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_item_lock_recode;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        SmartlockRecord smartlockRecord =mDataList.get(position);
        TextView tv_nameWithTime = holder.getView(R.id.tv_nameWithTime);
        tv_nameWithTime.setText(smartlockRecord.getRealName() + " "+
                LojaDateUtils.format(smartlockRecord.getUnlockTime(),LojaDateUtils.YYYY_MM_DD_HH_MM_SS_FORMAT));
        TextView tv_state = holder.getView(R.id.tv_state);
        if(smartlockRecord.getUnlockMode() .equalsIgnoreCase("10"))
            tv_state.setText("密码开锁");
        else  if(smartlockRecord.getUnlockMode() .equalsIgnoreCase("20"))
            tv_state.setText("指纹开锁");
        else  if(smartlockRecord.getUnlockMode() .equalsIgnoreCase("30"))
            tv_state.setText("IC卡开锁");
        else
            tv_state.setText("未知方式开锁");

        TextView tv_idcard = holder.getView(R.id.tv_idcard);
        tv_idcard.setText(mContext.getString(R.string.idcardMather,smartlockRecord.getIdentityCard()));
        TextView tv_tel = holder.getView(R.id.tv_tel);
        tv_tel.setText(mContext.getString(R.string.telMather,smartlockRecord.getMobile()));


    }
}
