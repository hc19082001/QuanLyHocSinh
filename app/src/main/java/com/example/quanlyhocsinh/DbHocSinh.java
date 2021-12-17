package com.example.quanlyhocsinh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHocSinh extends SQLiteOpenHelper {
    public static final String DB_NAME = "quanlyhocsinh";
    public static final int DB_VERSION = 2;
    public DbHocSinh(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       String sql_hocsinh = String.format( "CREATE TABLE %s ( " +
               "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
               "%s TEXT NOT NULL," +
               "%s TEXT NOT NULL," +
               "%s INTEGER NOT NULL," +
               "%s INTEGER NOT NULL," +
               "%s TEXT NOT NULL );",
               HocSinh.TB_NAME, HocSinh.COL_ID, HocSinh.COL_TEN, HocSinh.COL_LOP, HocSinh.COL_NAMSINH, HocSinh.COL_GIOITINH, HocSinh.COL_DIACHI);

        String sql_monhoc = String.format( "CREATE TABLE %s ( " +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL );",
                MonHoc.TB_MONHOC_NAME,  MonHoc.COL_MAMH, MonHoc.COL_TENMH, MonHoc.COL_SOTINCHI);

        db.execSQL(sql_monhoc);
       db.execSQL(sql_hocsinh);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
