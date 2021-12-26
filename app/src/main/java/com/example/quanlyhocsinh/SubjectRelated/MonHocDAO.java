package com.example.quanlyhocsinh.SubjectRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;

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

    public void deleteAllData() {
        open();
        sqLiteDatabase.delete(MonHoc.TB_MONHOC_NAME, null, null);
        close();
    }

    public void addListData(ArrayList<MonHoc> monHocs) {
        for (MonHoc x : monHocs) {
            addRow(x);
        }
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

    public ArrayList<MonHoc> getMonHocOf(int mahs) {
        open();

        ArrayList<MonHoc> monHocs = new ArrayList<MonHoc>();

        String sql = "SELECT tb_Monhoc.ma_mh, tb_Monhoc.ten_mh, tb_Monhoc.so_tinchi\n" +
                     "FROM tb_hocsinh INNER JOIN tb_diem ON tb_hocsinh.id_hs = tb_diem.id_hs INNER JOIN tb_Monhoc ON tb_Monhoc.ma_mh = tb_diem.ma_mh\n" +
                     "WHERE tb_hocsinh.id_hs = ?";
        String[] Args = new String[] {mahs +""};

        Cursor cursor = sqLiteDatabase.rawQuery(sql, Args);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MonHoc monHoc = new MonHoc();
            monHoc.setMa_mh(cursor.getInt(0));
            monHoc.setTen_mh(cursor.getString(1));
            monHoc.setSo_tinchi(cursor.getInt(2));

            monHocs.add(monHoc);
            cursor.moveToNext();
        }
        close();
        return monHocs;
    }

}
