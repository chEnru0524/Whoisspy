package com.example.asus.whoisspy;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.provider.BaseColumns._ID;

/**
 * Created by ASUS on 2017/2/15.
 */

public class Mysql_Database extends SQLiteOpenHelper{
    public static final String TABLE_NAME = "Questions";  //表格名稱
    public static final String QUESTION1 = "question1";
    public static final String QUESTION2 = "question2";
    private final static String DATABASE_NAME = "Who_Is_Spy.db";  //資料庫名稱

    private final static int DATABASE_VERSION = 1;  //資料庫版本
    public Mysql_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.print("已建立!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        final String INIT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION1 + " CHAR, " + QUESTION2 + " CHAR);";
        db.execSQL(INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
