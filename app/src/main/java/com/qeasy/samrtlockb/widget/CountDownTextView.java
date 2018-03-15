package com.qeasy.samrtlockb.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.qeasy.samrtlockb.R;


/**
 * Created by fancl on 2016/12/29.
 * 倒计时的textview
 */

public class CountDownTextView extends TextView {


    private final String TAG = CountDownTextView.class.getSimpleName();

    private String beforeStr;//点击之前的

    private Context mContext;

    public CountDownTextView(Context context) {
        this(context, null, 0);
    }

    private boolean isDowning = false;

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (TextUtils.isEmpty(this.getText())) {
            beforeStr = this.getText().toString();
        } else {
            beforeStr = getResources().getString(R.string.getVerificationCode);
        }

        initdate();
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private void initdate() {
//
//        Log.i(TAG, "initdate()");
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startTime();
//
//
//            }
//        });
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) millisUntilFinished / 1000;
            CountDownTextView.this.setText(mContext.getString(R.string.processTime, progress + ""));

        }

        @Override
        public void onFinish() {
            //mView.endTime();
            CountDownTextView.this.setEnabled(true);
            CountDownTextView.this.setText(beforeStr);
            isDowning =false;
            qualView();
        }
    };


    public void startTime() {
        unqualView();
        mCountDownTimer.start();
        CountDownTextView.this.setEnabled(false);
        isDowning =true;

    }


    public void cancelTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        qualView();
        CountDownTextView.this.setEnabled(true);
        CountDownTextView.this.setText(beforeStr);
        isDowning =false;
    }


    public void unqualView(){
        if(!isDowning) {
            this.setBackgroundResource(R.drawable.shape_regist_uncode);
            this.setTextColor(getResources().getColor(R.color.colorAccent));
            setEnabled(false);
        }
    }

    public void qualView(){
        if(isDowning)
            return;
        else {
//            this.setBackgroundResource(R.drawable.shape_regist_code);
            this.setTextColor(getResources().getColor(R.color.colorAccent));
            setEnabled(true);
        }
    }


}
