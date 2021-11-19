package com.example.quanlyhocsinh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHocSinh extends SQLiteOpenHelper {
    public static final String DB_NAME = "quanlyhocsinh.db";
    public static final int DB_VERSION = 1;
    public DbHocSinh(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql_hocsinh = "CREATE TABLE  tb_hocsinh  (\n" +
               "\t id_hs \tINTEGER NOT NULL,\n" +
               "\t ten_hs \tTEXT NOT NULL,\n" +
               "\t lop_hs \tTEXT NOT NULL,\n" +
               "\t tuoi_hs \tINTEGER NOT NULL,\n" +
               "\t gioitinh_hs \tTEXT NOT NULL,\n" +
               "\tPRIMARY KEY( id_hs  AUTOINCREMENT)\n" +
               ");";
       db.execSQL(sql_hocsinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
