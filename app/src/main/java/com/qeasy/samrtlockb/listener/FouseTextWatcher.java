package com.qeasy.samrtlockb.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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

public class FouseTextWatcher implements TextWatcher {


    private EditText focusEt;

    private int focusLenght;


    public FouseTextWatcher(EditText focusEt,int focusLenght){
        this.focusEt=focusEt;
        this.focusLenght =focusLenght;
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {



    }

    @Override
    public void afterTextChanged(Editable s) {

        if(s.length() ==focusLenght && focusEt != null)
            focusEt.requestFocus();

    }
}
