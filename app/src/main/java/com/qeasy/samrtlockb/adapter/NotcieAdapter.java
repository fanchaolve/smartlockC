package com.qeasy.samrtlockb.adapter;


import android.content.Context;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.recycleradapter.ListBaseAdapter;
import com.qeasy.samrtlockb.adapter.recycleradapter.SuperViewHolder;
import com.qeasy.samrtlockb.bean.Notice;
import com.qeasy.samrtlockb.utils.LojaDateUtils;

/**
 * ==============================================
 * <p>
 * 包名：com.qeasy.samrtlockb.adapter
 * <p>
 * 说明：todo
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/3/8
 * <p>
 * ==============================================
 */
public class NotcieAdapter extends ListBaseAdapter<Notice> {
    public NotcieAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_item_notice;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        Notice notice =mDataList.get(position);
        TextView tv_time = holder.getView(R.id.tv_time);
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_content = holder.getView(R.id.tv_content);
        tv_title.setText(notice.getTitle());
        tv_content.setText(notice.getContent());
        tv_time.setText(LojaDateUtils.format(notice.getReleaseTime(),LojaDateUtils.YYYY_MM_DD_HH_MM_CN_FORMAT));




    }
}
