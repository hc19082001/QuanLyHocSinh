package com.example.quanlyhocsinh.ClassRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;

import java.util.ArrayList;

public class LopDAO {

    DbHocSinh dbHocSinh;
    SQLiteDatabase sqLiteDatabase;

    public LopDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
    }
    public void open(){
        sqLiteDatabase = dbHocSinh.getWritableDatabase();
    }

    public void close(){
        dbHocSinh.close();
    }

    public long addRow(Lop lop){
        open();
        ContentValues values = new ContentValues();
        values.put(Lop.COL_TENLOP,lop.getTen_lop());
        values.put(Lop.COL_SOLUONG,lop.getSo_luong());

        long res = sqLiteDatabase.insert(Lop.DB_LOP_NAME,null,values);
        close();

        return res;
    }

    public int deleteRow(Lop lop){
        open();
        String [] arh = new String[]{lop.getMa_lop()+""};
        int res = sqLiteDatabase.delete(Lop.DB_LOP_NAME, Lop.COL_MALOP + " = ?",arh);
        close();
        return res;

    }

    public int updateRow(Lop lop){
        open();
        ContentValues values = new ContentValues();

        values.put(Lop.COL_TENLOP,lop.getTen_lop());
        values.put(Lop.COL_SOLUONG, lop.getSo_luong());

        String [] arh = new String[]{lop.getMa_lop()+""};

        int res = sqLiteDatabase.update(Lop.DB_LOP_NAME,values,Lop.COL_MALOP + "= ?",arh);
        close();
        return res;
    }

    public ArrayList<Lop> getAll(){
        open();

        ArrayList<Lop> lops = new ArrayList<Lop>();

        String [] ds_cot = new String[]{"*"};

        Cursor cursor = sqLiteDatabase.query(Lop.DB_LOP_NAME,ds_cot,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Lop lop = new Lop();
                lop.setMa_lop(cursor.getInt(0));
                lop.setTen_lop(cursor.getString(1));
                lop.setSo_luong(cursor.getInt(2));

                lops.add(lop);
                cursor.moveToNext();
            }
        }
        close();
        return lops;
    }

    public int getSLHSmotLop(int malop) {
        open();
        int sl = 0;
        String[] selectionArg = new String[] {malop + ""};
        String query = "SELECT * FROM tb_hocsinh INNER JOIN Tb_Lop ON Tb_Lop.ma_lop = tb_hocsinh.lop_hs WHERE Tb_Lop.ma_lop = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, selectionArg);
        sl = cursor.getCount();
        cursor.moveToLast();
        return sl;
    }



    public ArrayList<Lop> search(String tenlop, int malop){
        sqLiteDatabase = dbHocSinh.getWritableDatabase();

        ArrayList<Lop> lops = new ArrayList<>();

        String [] dsCot = new String []{"*"};

        String dieukien = Lop.COL_TENLOP + " = ? " + " OR " + Lop.COL_MALOP + " = ? ";

        String[] giatri = new String[] {tenlop+"", malop+""};

        Cursor cursor = sqLiteDatabase.query(Lop.DB_LOP_NAME, dsCot, dieukien, giatri, null, null, null);

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

                Lop lop = new Lop();
                lop.setMa_lop(cursor.getInt(0));
                lop.setTen_lop(cursor.getString(1));
                lop.setSo_luong(cursor.getInt(2));

                lops.add(lop);
                cursor.moveToNext();
            }
        }
        return lops;
    }


}
