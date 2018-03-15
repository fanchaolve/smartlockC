package com.qeasy.samrtlockb.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/1/18.
 * 虚线
 */

public class HomeLineItemDecoration extends RecyclerView.ItemDecoration {


    private Context mContext;

    public HomeLineItemDecoration(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
//            if (i == 2) {
//                params.setMargins(0, Utils.dp2px(mContext, -50), 0, 0);
//            }else {
//                params.setMargins(0, 0, 0, 0);
//            }
//            child.setLayoutParams(params);

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
