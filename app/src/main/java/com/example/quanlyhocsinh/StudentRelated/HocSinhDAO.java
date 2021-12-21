package com.example.quanlyhocsinh.StudentRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.Database.DbHocSinh;

public class HocSinhDAO {
    SQLiteDatabase database;
    DbHocSinh dbHocSinh;

    public HocSinhDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
    }

    public void open() {
        database = dbHocSinh.getWritableDatabase();
    }

    public void close() {
        dbHocSinh.close();
    }

    //------------------------------------------

    public long addRow(HocSinh hocSinh) {

        ContentValues values = new ContentValues();

        values.put(HocSinh.COL_TEN, hocSinh.getTen_hs());
        values.put(HocSinh.COL_LOP, hocSinh.getLop_hs());
        values.put(HocSinh.COL_NAMSINH, hocSinh.getNs_hs());
        values.put(HocSinh.COL_DIACHI, hocSinh.getDiachi_hs());

        if (hocSinh.isGioitinh_hs()) {
            values.put(HocSinh.COL_GIOITINH, 1);
        } else {
            values.put(HocSinh.COL_GIOITINH, 0);
        }

        long res = database.insert(HocSinh.TB_NAME, null, values);

        return res;
    }

    public int deleteRow(HocSinh hocSinh) {
        String[] arh = new String[]{hocSinh.getId_hs() + ""};

        int res = database.delete(HocSinh.TB_NAME, "id_hs = ?", arh);
        return res;
    }

    public int updateRow(HocSinh hocSinh) {
        ContentValues values = new ContentValues();

        values.put(HocSinh.COL_TEN, hocSinh.getTen_hs());
        values.put(HocSinh.COL_LOP, hocSinh.getLop_hs());
        values.put(HocSinh.COL_NAMSINH, hocSinh.getNs_hs());
        values.put(HocSinh.COL_DIACHI, hocSinh.getDiachi_hs());

        if (hocSinh.isGioitinh_hs()) {
            values.put(HocSinh.COL_GIOITINH, 1);
        } else {
            values.put(HocSinh.COL_GIOITINH, 0);
        }

        String[] arh = new String[]{hocSinh.getId_hs() + ""};

        int res = database.update(HocSinh.TB_NAME, values, "id_hs = ?", arh);
        return res;
    }

    public ArrayList<HocSinh> getAll() {
        ArrayList<HocSinh> listHocSinhs = new ArrayList<>();

        String[] ds_cot = new String[]{"*"};

        Cursor cursor = database.query(HocSinh.TB_NAME, ds_cot, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HocSinh hocSinh = new HocSinh();
                hocSinh.setId_hs(cursor.getInt(0));
                hocSinh.setTen_hs(cursor.getString(1));
                hocSinh.setLop_hs(cursor.getInt(2));
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

    public int getSLHSmotLop(int malop) {
        open();
        int sl = 0;
        String[] selectionArg = new String[]{malop + ""};
        String query = "SELECT * FROM tb_hocsinh INNER JOIN Tb_Lop ON Tb_Lop.ma_lop = tb_hocsinh.lop_hs WHERE Tb_Lop.ma_lop = ?";
        Cursor cursor = database.rawQuery(query, selectionArg);
        sl = cursor.getCount();
        return sl;


    }

    public ArrayList<HocSinh> search() {
        database = dbHocSinh.getWritableDatabase();

        ArrayList<HocSinh> hocSinhs = new ArrayList<>();

        String[] dsCot = new String[]{"*"};

        String dieukien = HocSinh.COL_TEN + " =? " + "AND" + HocSinh.COL_LOP + "=?" + "AND" + HocSinh.COL_NAMSINH + "=?" + "AND" +HocSinh.COL_GIOITINH;

        Cursor cursor = database.query(Lop.DB_LOP_NAME, dsCot, dieukien, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                int ID = cursor.getInt(0);//lấy giá trị của cột ID
              /*  String ten = cursor.getString(1);
                String tk = cursor.getString(2);
                String mk = cursor.getString(3);*/


                HocSinh hs = new HocSinh();
                hs.setId_hs(cursor.getInt(0));
                hs.setTen_hs(cursor.getString(1));
                hs.setDiachi_hs(cursor.getString(2));
                hs.setLop_hs(cursor.getInt(3));

                hocSinhs.add(hs);


                cursor.moveToNext();
            }
        }
        return hocSinhs;

    }
}
