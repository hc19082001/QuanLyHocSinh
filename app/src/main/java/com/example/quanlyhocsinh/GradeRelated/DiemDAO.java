package com.example.quanlyhocsinh.GradeRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.ArrayList;

public class DiemDAO {

    public DbHocSinh dbHocSinh;
    public SQLiteDatabase sqLiteDatabase;

    public DiemDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
    }

    public void deleteAllData() {
        open();
        sqLiteDatabase.delete(Diem.DIEM_DB_NAME, null, null);
        close();
    }

    public void addListData(ArrayList<Diem> diems) {
        for (Diem x : diems) {
            addRow(x);
        }
    }

    public void open(){
        sqLiteDatabase = dbHocSinh.getWritableDatabase();
    }

    public void close(){
        dbHocSinh.close();
    }

    public long addRow(Diem diem){
        open();
        ContentValues values = new ContentValues();
        values.put(diem.COL_IDHS,diem.getId_hs());
        values.put(diem.COL_MAMH,diem.getMa_mh());
        values.put(diem.COL_DQT,diem.getDiemQT());
        values.put(diem.COL_DGK,diem.getDiemGK());
        values.put(diem.COL_DCK,diem.getDiemCK());

        long res = sqLiteDatabase.insert(Diem.DIEM_DB_NAME,null,values);
        close();

        return res;
    }

    public int deleteRow(Diem diem){
        open();
        String whClause = Diem.COL_IDHS + " = ? " + " AND " + Diem.COL_MAMH + " = ? ";
        String [] arh = new String[]{diem.getId_hs()+"", diem.getMa_mh() +""};
        int res = sqLiteDatabase.delete(Diem.DIEM_DB_NAME, whClause,arh);
        close();
        return res;
    }

    public int updateRow(Diem diem){
        open();
        ContentValues values = new ContentValues();

        values.put(Diem.COL_DQT,diem.getDiemQT());
        values.put(Diem.COL_DGK,diem.getDiemGK());
        values.put(Diem.COL_DCK,diem.getDiemCK());

        String whClause = Diem.COL_IDHS + " = ? " + " AND " + Diem.COL_MAMH + " = ? ";
        String [] arh = new String[]{diem.getId_hs()+"", diem.getMa_mh() +""};

        int res = sqLiteDatabase.update(Diem.DIEM_DB_NAME,values,whClause,arh);
        close();
        return res;
    }

    public ArrayList<Diem> getAll(){
        open();

        ArrayList<Diem> diems = new ArrayList<Diem>();

        String [] ds_cot = new String[]{"*"};

        Cursor cursor = sqLiteDatabase.query(Diem.DIEM_DB_NAME,ds_cot,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Diem diem = new Diem();

                diem.setId_hs(cursor.getInt(0));
                diem.setMa_mh(cursor.getInt(1));

                diem.setDiemQT(cursor.getDouble(2));
                diem.setDiemGK(cursor.getDouble(3));
                diem.setDiemCK(cursor.getDouble( 4));

                diems.add(diem);
                cursor.moveToNext();
            }
        }
        close();
        return diems;
    }

    public Diem getDiemHS(int id, int mmh) {
        open();

        Diem diem = new Diem();

        String sql = "SELECT tb_diem.id_hs, tb_diem.ma_mh, tb_diem.diemQT, tb_diem.diemGK, tb_diem.diemCK \n " +
                     "FROM tb_hocsinh INNER JOIN tb_diem ON tb_hocsinh.id_hs = tb_diem.id_hs INNER JOIN tb_Monhoc ON tb_Monhoc.ma_mh = tb_diem.ma_mh \n " +
                     "WHERE tb_hocsinh.id_hs = ? AND tb_Monhoc.ma_mh = ? ";
        String[] Args = new String[] {id+"", mmh +""};
        Cursor cursor = sqLiteDatabase.rawQuery(sql, Args);

        cursor.moveToFirst();
        diem.setId_hs(cursor.getInt(0));
        diem.setMa_mh(cursor.getInt(1));
        diem.setDiemQT(cursor.getDouble(2));
        diem.setDiemGK(cursor.getDouble(3));
        diem.setDiemCK(cursor.getDouble(4));
        cursor.moveToLast();
        close();

        return diem;
    }



}
