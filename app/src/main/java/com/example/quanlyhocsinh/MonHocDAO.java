package com.example.quanlyhocsinh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MonHocDAO {
    SQLiteDatabase sqLiteDatabase;
    DbHocSinh dbHocSinh;

    public MonHocDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
    }

    public void open(){
        sqLiteDatabase = dbHocSinh.getWritableDatabase();
    }

    public void close(){
        dbHocSinh.close();
    }

    public long addRow(MonHoc monHoc){
        open();
        ContentValues values = new ContentValues();
        values.put(MonHoc.COL_TENMH,monHoc.getTen_mh());
        values.put(MonHoc.COL_SOTINCHI,monHoc.getSo_tinchi());

        long res = sqLiteDatabase.insert(MonHoc.TB_MONHOC_NAME,null,values);
        close();

        return res;
    }

    public int deleteRow(MonHoc monHoc){
        open();
        String [] arh = new String[]{monHoc.getMa_mh()+""};
        int res = sqLiteDatabase.delete(MonHoc.TB_MONHOC_NAME, MonHoc.COL_MAMH + " = ?",arh);
        close();
        return res;

    }

    public int updateRow(MonHoc monHoc){
        open();
        ContentValues values = new ContentValues();

        values.put(MonHoc.COL_TENMH,monHoc.getTen_mh());
        values.put(MonHoc.COL_SOTINCHI, monHoc.getSo_tinchi());

        String [] arh = new String[]{monHoc.getMa_mh()+""};

        int res = sqLiteDatabase.update(MonHoc.TB_MONHOC_NAME,values,MonHoc.COL_MAMH + "= ?",arh);
        close();
        return res;
    }

    public ArrayList<MonHoc> getAll(){
        open();

        ArrayList<MonHoc> monHocs = new ArrayList<MonHoc>();

        String [] ds_cot = new String[]{"*"};

        Cursor cursor = sqLiteDatabase.query(MonHoc.TB_MONHOC_NAME,ds_cot,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                MonHoc monHoc = new MonHoc();
                monHoc.setMa_mh(cursor.getInt(0));
                monHoc.setTen_mh(cursor.getString(1));
                monHoc.setSo_tinchi(cursor.getInt(2));

                monHocs.add(monHoc);
                cursor.moveToNext();
            }
        }
        close();
        return monHocs;
    }
}
