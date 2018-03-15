package com.qeasy.samrtlockb.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.listener
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2018/1/5
 * <p>
 * ==============================================
 */

public class SlashTextWatcher implements TextWatcher {

    private EditText targetEt;

    public SlashTextWatcher(EditText targetEt){
        this.targetEt=targetEt;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        int length = s.toString().length();
        //删除数字
        if (count == 0) {
            if (length == 3) {
                targetEt.setText(s.subSequence(0, 2).toString());
            }
        }

        //添加数字
        if (count == 1) {
            if (length == 3) {
                String part1 = s.subSequence(0, 2).toString();
                String part2 = s.subSequence(2, length).toString();
                targetEt.setText(part1 + "/" + part2);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        //将光标移动到末尾
        targetEt.setSelection(targetEt.getText().toString().length());
    }
}
