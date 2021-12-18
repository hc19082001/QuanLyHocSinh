package com.example.quanlyhocsinh.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.GradeRelated.Diem;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;

public class DbHocSinh extends SQLiteOpenHelper {
    public static final String DB_NAME = "quanlyhocsinh";
    public static final int DB_VERSION = 5;
    public DbHocSinh(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql_hocsinh = String.format( "CREATE TABLE %s ( " +
               "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
               "%s TEXT NOT NULL," +
               "%s INTEGER NOT NULL," +
               "%s INTEGER NOT NULL," +
               "%s INTEGER NOT NULL," +
               "%s TEXT NOT NULL, " +
                       "FOREIGN KEY (%s) REFERENCES %s (%s) );",
               HocSinh.TB_NAME, HocSinh.COL_ID, HocSinh.COL_TEN, HocSinh.COL_LOP,
               HocSinh.COL_NAMSINH, HocSinh.COL_GIOITINH, HocSinh.COL_DIACHI,
               HocSinh.COL_LOP, Lop.DB_LOP_NAME, Lop.COL_MALOP);

        String sql_monhoc = String.format( "CREATE TABLE %s ( " +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL );",
                MonHoc.TB_MONHOC_NAME,  MonHoc.COL_MAMH, MonHoc.COL_TENMH, MonHoc.COL_SOTINCHI);

        String sql_lop = String.format( "CREATE TABLE %s ( " +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL );",
                Lop.DB_LOP_NAME,  Lop.COL_MALOP, Lop.COL_TENLOP, Lop.COL_SOLUONG);

        String sql_diem = String.format( "CREATE TABLE %s ( " +
                        "%s INTEGER," +
                        "%s INTEGER," +
                        "%s REAL NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s REAL NOT NULL," +
                        "PRIMARY KEY (%s, %s),  " +
                        "FOREIGN KEY (%s) REFERENCES %s (%s)" +
                        "FOREIGN KEY (%s) REFERENCES %s (%s) );",
                Diem.DIEM_DB_NAME,  Diem.COL_IDHS, Diem.COL_MAMH, Diem.COL_DQT, Diem.COL_DGK, Diem.COL_DCK,
                Diem.COL_MAMH, Diem.COL_IDHS,
                Diem.COL_IDHS, HocSinh.TB_NAME, HocSinh.COL_ID,
                Diem.COL_MAMH, MonHoc.TB_MONHOC_NAME, MonHoc.COL_MAMH);

       db.execSQL(sql_hocsinh);
       db.execSQL(sql_monhoc);
       db.execSQL(sql_lop);
       db.execSQL(sql_diem);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
