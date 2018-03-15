package com.qeasy.samrtlockb.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.qeasy.samrtlockb.widget.CountDownTextView;


/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.listener
 * <p>
 * 说明：手机号码固定空格
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/4
 * <p>
 * ==============================================
 */

public class PhoneTextWatcher2 implements TextWatcher {


    private EditText targetEt;

    private CountDownTextView countDownTextView;

    private EditText focusEt;


    private int cursor;

    private int beforCurosor;

    public PhoneTextWatcher2(EditText targetEt) {
        this.targetEt = targetEt;
    }

    public PhoneTextWatcher2(EditText targetEt, CountDownTextView countDownTextView) {
        this.targetEt = targetEt;
        this.countDownTextView = countDownTextView;
    }



    public PhoneTextWatcher2(EditText targetEt, CountDownTextView countDownTextView,
                             EditText focusEt) {
        this.targetEt = targetEt;
        this.countDownTextView = countDownTextView;
        this.focusEt =focusEt;
    }

    public PhoneTextWatcher2(EditText targetEt,
                             EditText focusEt) {
        this.targetEt = targetEt;
        this.focusEt =focusEt;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforCurosor = targetEt.getSelectionStart();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (targetEt.getSelectionStart() != 0 )
            cursor = targetEt.getSelectionStart();

        s = s.toString().replaceAll(" ", "");
        //删除数字
        if (count == 0) {
            if(beforCurosor ==1 && targetEt.getSelectionStart()==0)
                cursor=0;
            if (s.length() > 7) {
                String part1 = s.subSequence(0, 3).toString();
                String part2 = s.subSequence(3, 7).toString();
                String part3 = s.subSequence(7, s.length()).toString();
                targetEt.setText(part1 + " " + part2 + " " + part3);
            } else if (s.length() > 3) {
                String part1 = s.subSequence(0, 3).toString();
                String part2 = s.subSequence(3, s.length()).toString();
                targetEt.setText(part1 + " " + part2);
            } else if (s.length() <= 3 && s.length() > 0) {
                targetEt.setText(s);
            }
        }

        //添加数字
        if (count == 1) {
            if (s.length() > 7) {
                String part1 = s.subSequence(0, 3).toString();
                String part2 = s.subSequence(3, 7).toString();
                String part3 = s.subSequence(7, s.length()).toString();
                targetEt.setText(part1 + " " + part2 + " " + part3);
            } else if (s.length() > 3) {
                String part1 = s.subSequence(0, 3).toString();
                String part2 = s.subSequence(3, s.length()).toString();
                targetEt.setText(part1 + " " + part2);
            }
        }

        if (count == 1) {
            if (cursor == 4) {
                targetEt.setSelection(cursor + 1);
            }else if(cursor ==9){
                targetEt.setSelection(cursor + 1);
            }else {
                targetEt.setSelection(cursor);
            }
        }

        if(count == 0){
            if (cursor == 4) {
                targetEt.setSelection(cursor - 1);
            }else if(cursor ==9){
                targetEt.setSelection(cursor - 1);
            }else {
                if(targetEt.getText().length() ==0)
                    targetEt.setSelection(0);
                else {
                    targetEt.setSelection(cursor);
                }
            }
        }


    }

    @Override
    public void afterTextChanged(Editable s) {
        //将光标移动到末尾


        if(focusEt != null){
            if (targetEt.getText().toString().length() == 13)
                focusEt.requestFocus();
        }

        if (countDownTextView == null)
            return;
        if (targetEt.getText().toString().length() == 13)
            countDownTextView.qualView();
        else
            countDownTextView.unqualView();

    }
}
