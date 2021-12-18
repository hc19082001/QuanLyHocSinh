package com.example.quanlyhocsinh.StudentRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.quanlyhocsinh.Database.DbHocSinh;

public class HocSinhDAO {
    SQLiteDatabase database;
    DbHocSinh dbHocSinh;

    public HocSinhDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
    }

    public void open(){
        database = dbHocSinh.getWritableDatabase();
    }

    public void close(){
        dbHocSinh.close();
    }

    //------------------------------------------

    public long addRow(HocSinh hocSinh){
        ContentValues values = new ContentValues();
        values.put(HocSinh.COL_TEN,hocSinh.getTen_hs());
        values.put(HocSinh.COL_LOP,hocSinh.getLop_hs());
        values.put(HocSinh.COL_NAMSINH,hocSinh.getNs_hs());
        values.put(HocSinh.COL_DIACHI,hocSinh.getDiachi_hs());

        if (hocSinh.isGioitinh_hs()) {
            values.put(HocSinh.COL_GIOITINH,1);
        } else {
            values.put(HocSinh.COL_GIOITINH,0);
        }

        long res = database.insert(HocSinh.TB_NAME,null,values);

        return res;
    }

    public int deleteRow(HocSinh hocSinh){
        String [] arh = new String[]{hocSinh.getId_hs()+""};

        int res = database.delete(HocSinh.TB_NAME,"id_hs = ?",arh);
        return res;
    }

    public int updateRow(HocSinh hocSinh){
        ContentValues values = new ContentValues();

        values.put(HocSinh.COL_TEN,hocSinh.getTen_hs());
        values.put(HocSinh.COL_LOP,hocSinh.getLop_hs());
        values.put(HocSinh.COL_NAMSINH,hocSinh.getNs_hs());
        values.put(HocSinh.COL_DIACHI,hocSinh.getDiachi_hs());
        if (hocSinh.isGioitinh_hs()) {
            values.put(HocSinh.COL_GIOITINH,1);
        } else {
            values.put(HocSinh.COL_GIOITINH,0);
        }

        String [] arh = new String[]{hocSinh.getId_hs()+""};

        int res = database.update(HocSinh.TB_NAME,values,"id_hs = ?",arh);
        return res;
    }

    public ArrayList<HocSinh> getAll(){
        ArrayList<HocSinh> listHocSinhs = new ArrayList<>();

        String [] ds_cot = new String[]{"*"};

        Cursor cursor = database.query(HocSinh.TB_NAME,ds_cot,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                HocSinh hocSinh = new HocSinh();
                hocSinh.setId_hs(cursor.getInt(0));
                hocSinh.setTen_hs(cursor.getString(1));
                hocSinh.setLop_hs(cursor.getString(2));
                hocSinh.setNs_hs(cursor.getInt(3));

                if (cursor.getInt(4) == 1) {
                    hocSinh.setGioitinh_hs(true);
                } else {
                    hocSinh.setGioitinh_hs(false);
                }
                hocSinh.setDiachi_hs(cursor.getString(5));

                listHocSinhs.add(hocSinh);
                cursor.moveToNext();
            }
        }
        return listHocSinhs;
    }
}
