package com.qeasy.samrtlockb.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.listener.exts.Act0;
import com.qeasy.samrtlockb.utils.ScreenUtil;


public class StopUseDialog extends Dialog {

    private Context context;
    private String tip;
    private Act0 callback;

    public StopUseDialog(Context context, String tip, Act0 callback) {
        super(context, R.style.myDialogTheme);
        this.context = context;
        this.tip = tip;
        this.callback = callback;
        initView();
    }


    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_stop_use, null);

        layout.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        layout.findViewById(R.id.tv_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null)
                    callback.run();
                dismiss();
            }
        });


        setContentView(layout);

        // 设置对话框的出现位置，借助于window对象
        Window win = getWindow();
        win.setGravity(Gravity.CENTER);
        // win.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//
        // 弹出对话框时，底部窗体，不变暗。

        WindowManager.LayoutParams lp = win.getAttributes();
        // lp.x = -200;// x=0,y=0时，显示位置是屏幕中心。
        // lp.y = 0;
        // lp.alpha = 0.6f;// 对话框的透明度
        lp.width = (int) (ScreenUtil.getScrrenWidth() * 0.75);

        //lp.width=500;
        win.setAttributes(lp);
//        win.setWindowAnimations(R.style.BT_DialogAnimStyle);
        setCancelable(false);
    }


}
