package com.qeasy.samrtlockb.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.adapter.recycleradapter.ListBaseAdapter;
import com.qeasy.samrtlockb.adapter.recycleradapter.SuperViewHolder;
import com.qeasy.samrtlockb.bean.Notice;
import com.qeasy.samrtlockb.bean.Unnormal;
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
public class UnnormalAdapter extends ListBaseAdapter<Unnormal> {
    public UnnormalAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_unnormal;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        final Unnormal unnormal =mDataList.get(position);

        ImageView iv_select = holder.getView(R.id.iv_select);
        TextView tv_memo = holder.getView(R.id.tv_memo);
        final EditText et_memo = holder.getView(R.id.et_memo);
        LinearLayout lin_line =holder.getView(R.id.lin_line);
        if(unnormal.isSelect())
            iv_select.setVisibility(View.VISIBLE);
        else
            iv_select.setVisibility(View.INVISIBLE);

        if(position ==mDataList.size() -1){
            tv_memo.setVisibility(View.GONE);
            et_memo.setVisibility(View.VISIBLE);
            lin_line.setVisibility(View.GONE);
            et_memo.setText(unnormal.getContent());
            et_memo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    unnormal.setContent(s+"");
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else {
            tv_memo.setVisibility(View.VISIBLE);
            et_memo.setVisibility(View.GONE);
            lin_line.setVisibility(View.VISIBLE);
            tv_memo.setText(unnormal.getContent());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(unnormal.isSelect())
                    return;
                else {
                    for( Unnormal unnormal1  : mDataList){
                         unnormal1.setSelect(false);
                    }
                    unnormal.setSelect(true);
                    notifyDataSetChanged();
                }

            }
        });


    }


}
