package com.qeasy.samrtlockb.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qeasy.samrtlockb.MyApplication;
import com.qeasy.samrtlockb.bean.User;

/**
 * Created by Administrator on 2017/1/3.
 */

public class DatabaseImpl extends SQLiteOpenHelper implements Database {


    private static DatabaseImpl helper;

    private static final String DB_NAME = "db_smartlock.db";



    private static final String TABLE_USER = "db_user";



    private static final int version = 1;

    public static SQLiteDatabase db;

    private DatabaseImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static synchronized DatabaseImpl getInstance() {
        if (helper == null) {
            helper = new DatabaseImpl(MyApplication.getAppContext(), DB_NAME, null, version);//数据库名称为create_db。
        }
        return helper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_USER +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "accessToken VARCHAR,memberId VARCHAR," +
                "mobile VARCHAR,expiresIn VARCHAR"
                 + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    @Override
    public void addUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) return;
        ContentValues values = new ContentValues();
        values.putAll(new UserDatabaseBuilder().deconstruct(user));
        String[] whereArgs = {"1"};
        int row_count = db.update(TABLE_USER, values, "id=?", whereArgs);
        if (row_count == 0) {
              db.insert(TABLE_USER, null, values);
        }

        db.close();
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public User getUser() {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) return user;

        String[] whereArgs = {"1"};
        Cursor query = db.query(TABLE_USER, null, "id = ?", whereArgs, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                user = new UserDatabaseBuilder().build(query);
            }
        }
        query.close();
        db.close();
        return user;
    }

    @Override
    public void removeUser() {
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) return;

        String[] whereArgs = {"1"};

        db.delete(TABLE_USER, "id=?", whereArgs);

        db.close();
    }
}
