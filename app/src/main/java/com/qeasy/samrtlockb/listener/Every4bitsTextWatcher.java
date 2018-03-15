package com.qeasy.samrtlockb.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.listener
 * <p>
 * 说明：每4位的空格
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/4
 * <p>
 * ==============================================
 */

public class Every4bitsTextWatcher implements TextWatcher {


    private EditText targetEt;

    int beforeTextLength = 0;//之前的长度
    int onTextLength = 0;//之后的长度

    public Every4bitsTextWatcher(EditText targetEt) {
        this.targetEt = targetEt;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        beforeTextLength = s.length();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Log.i("fancl", "输入的数字：" + s);
//        String bankNumber = targetEt.getText().toString().trim();
//        onTextLength = bankNumber.length();
//        if (onTextLength > beforeTextLength) {
//            if (bankNumber.length() == 5 || bankNumber.length() == 10 || bankNumber.length() == 15 || bankNumber.length() == 20) {
//                targetEt.setText(new StringBuffer(bankNumber).insert(
//                        bankNumber.length() - 1, " ").toString());
//                targetEt.setSelection(targetEt.getText()
//                        .length());
//            }
//        } else {
//            if (bankNumber.startsWith(" ")) {
//                targetEt.setText(new StringBuffer(bankNumber).delete(
//                        onTextLength - 1, onTextLength).toString());
//                targetEt.setSelection(targetEt.getText()
//                        .length());
//            }
//        }

        int length = s.toString().length();


        //删除数字
        if (count == 0) {
            if (length > 0 && length % 5 == 0) {
                targetEt.setText(s.subSequence(0, length - 1));
            }

        }

        //添加数字
        if (count == 1) {
            if (length > 0 && length % 5 == 0) {
                String part1 = s.subSequence(0, length - 1).toString();
                String part2 = s.subSequence(length - 1, length).toString();
                targetEt.setText(part1 + " " + part2);
            }
        }


    }

    @Override
    public void afterTextChanged(Editable s) {
        //将光标移动到末尾
        targetEt.setSelection(targetEt.getText().toString().length());
    }
}
