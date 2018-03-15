package com.qeasy.samrtlockb.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.qeasy.samrtlockb.MyApplication;
import com.qeasy.samrtlockb.R;
import com.qeasy.samrtlockb.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.widget
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/1
 * <p>
 * ==============================================
 */

public class AbstactDailog extends Dialog {




    @BindView(R.id.tv_negative)
    TextView tv_negative;

    @BindView(R.id.tv_positive)
    TextView tv_positive;

    @BindView(R.id.tv_title)
    TextView tv_title;



    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.iv_pic)
    ImageView iv_pic;


    private Context mContext;

    public AbstactDailog(@NonNull Context context) {
        super(context, R.style.processDialog);
        mContext = context;
        setContentView(R.layout.dialog_all);
        ButterKnife.bind(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();

    }


    //配置弹出框的大小和位置
    private void setWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = MyApplication.widthPixels - Utils.dp2px(mContext, 50); // 设置宽度
        lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        setCancelable(false);

    }

    //加内容
    public  AbstactDailog setContent(CharSequence content){
        tv_content.setText(content);
        return this;
    }



    public AbstactDailog setTitle(String title){
        tv_title.setText(title);
        return this;
    }

    public AbstactDailog setBitmap(Bitmap bitmap){
        iv_pic.setImageBitmap(bitmap);
        iv_pic.setVisibility(View.VISIBLE);
        return this;
    }

    public AbstactDailog setmPositiveButtonListener(OnClickListener mPositiveButtonListener,
                                           String text) {
        setButton( DialogInterface.BUTTON_POSITIVE,text,mPositiveButtonListener);
        return this;
    }

    public AbstactDailog setmNegativeButtonListener(OnClickListener mNegativeButtonListener,String text) {
        setButton( DialogInterface.BUTTON_NEGATIVE,text,mNegativeButtonListener);
        return this;
    }


    public void setButton(int whichButton, CharSequence text,
                          final OnClickListener listener) {

        if(listener==null)
            return;
        switch (whichButton){
            case DialogInterface.BUTTON_POSITIVE:
                this.tv_positive.setVisibility(View.VISIBLE);
                this.tv_positive.setText(text);
                this.tv_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(AbstactDailog.this, DialogInterface.BUTTON_POSITIVE);
                    }
                });
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                this.tv_negative.setVisibility(View.VISIBLE);
                this.tv_negative.setText(text);
                this.tv_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(AbstactDailog.this, DialogInterface.BUTTON_NEGATIVE);
                    }
                });
                break;
        }

    }
}
