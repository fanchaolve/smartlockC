package com.qeasy.samrtlockb.db;

import android.content.ContentValues;
import android.database.Cursor;

import com.qeasy.samrtlockb.bean.User;

/**
 * ==============================================
 * <p>
 * 包名：com.zm.bnh.db
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/12/26
 * <p>
 * ==============================================
 */

public class UserDatabaseBuilder extends DatabaseBuilder<User> {


    @Override
    public User build(Cursor query) {
        int accessToken = query.getColumnIndex("accessToken");
        int memberId = query.getColumnIndex("memberId");
        int mobile = query.getColumnIndex("mobile");
        int expiresIn = query.getColumnIndex("expiresIn");
        User user = new User();
        user.setAccessToken(query.getString(accessToken));
        user.setMemberId(query.getString(memberId));
        user.setMobile(query.getString(mobile));
        user.setExpiresIn(query.getString(expiresIn));
        return user;
    }


    @Override
    public ContentValues deconstruct(User user) {
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("accessToken", user.getAccessToken());
        values.put("memberId", user.getMemberId());
        values.put("mobile", user.getMobile());
        values.put("expiresIn", user.getExpiresIn());
        return values;
    }
}
