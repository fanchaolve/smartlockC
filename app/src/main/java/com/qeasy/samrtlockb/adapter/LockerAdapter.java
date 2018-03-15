package com.qeasy.samrtlockb.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.recycleradapter.BaseMultiAdapter;
import com.qeasy.samrtlockb.adapter.recycleradapter.ListBaseAdapter;
import com.qeasy.samrtlockb.adapter.recycleradapter.SuperViewHolder;
import com.qeasy.samrtlockb.bean.Member;
import com.qeasy.samrtlockb.bean.SmarLock;
import com.qeasy.samrtlockb.listener.LockListener;
import com.qeasy.samrtlockb.utils.LojaDateUtils;
import com.qeasy.samrtlockb.widget.AbstactDailog;

public class LockerAdapter extends ListBaseAdapter<Member> {



    private LockListener lockListener;

    public void setLockListener(LockListener lockListener) {
        this.lockListener = lockListener;
    }

    public LockerAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_item_open_lock_p;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final Member member =getDataList().get(position);
        TextView tv_timetotime = holder.getView(R.id.tv_timetotime);
        TextView tv_state = holder.getView(R.id.tv_state);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_idcard = holder.getView(R.id.tv_idcard);
        TextView tv_tel = holder.getView(R.id.tv_tel);
        LinearLayout lin_A = holder.getView(R.id.lin_A);
        TextView tv_stop = holder.getView(R.id.tv_stop);
        TextView tv_edit = holder.getView(R.id.tv_edit);
        TextView tv_story1 = holder.getView(R.id.tv_story1);
        LinearLayout lin_B = holder.getView(R.id.lin_B);
        TextView tv_use = holder.getView(R.id.tv_use);
        TextView tv_story2 = holder.getView(R.id.tv_story2);

        tv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockListener != null)
                    lockListener.stop(position,member);
            }
        });
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockListener != null)
                    lockListener.edit(position,member);
            }
        });

        tv_story1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockListener != null)
                    lockListener.recode(position,member);
            }
        });

        tv_story2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockListener != null)
                    lockListener.recode(position,member);
            }
        });

        tv_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lockListener != null)
                    lockListener.start(position,member);
            }
        });


        tv_timetotime.setText(mContext.getString(R.string.timetotime,
                LojaDateUtils.format(member.getUseStartTime(),LojaDateUtils.YYYY_MM_DD_FORMAT),
                LojaDateUtils.format(member.getUseEndTime(),LojaDateUtils.YYYY_MM_DD_FORMAT)));
        tv_state.setText("");

        tv_state.setText(tv_state.getText().toString()+ (member.getIsPinCode()==10 ? "密码锁+" : ""));
        tv_state.setText(tv_state.getText().toString()+ (member.getIsIcCode()==10 ? "IC卡锁+" : ""));
        tv_state.setText(tv_state.getText().toString()+ (member.getIsFingerprintCode()==10 ? "指纹锁" : ""));
        String stateTip =tv_state.getText().toString();
        if(stateTip.length()>1 &&  stateTip.endsWith("+")){
            stateTip = stateTip.substring(0,stateTip.length()-1);
            tv_state.setText(stateTip);
        }

        tv_name.setText(member.getRealName());
        tv_idcard.setText(mContext.getString(R.string.idcardMather,member.getIdentityCard()));
        tv_tel.setText(mContext.getString(R.string.telMather,member.getMobile()));
        if(member.getStatus() ==10){
            lin_A.setVisibility(View.VISIBLE);
            lin_B.setVisibility(View.GONE);
        }else {
            lin_A.setVisibility(View.GONE);
            lin_B.setVisibility(View.VISIBLE);
        }

    }


}
