package com.example.quanlyhocsinh.SubjectRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;

public class MonHocDAO {
    SQLiteDatabase sqLiteDatabase;
    DbHocSinh dbHocSinh;

    RequestQueue requestQueue;

    String urlAdd = "http://khang123-001-site1.dtempurl.com/QuanLyMonHoc/Create";
    String urlUpdate = "http://khang123-001-site1.dtempurl.com/QuanLyMonHoc/Edit";
    String urlDelete = "http://khang123-001-site1.dtempurl.com/QuanLyMonHoc/Delete";

    public MonHocDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
        requestQueue = Volley.newRequestQueue(context);
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

    public void addDataToWeb(MonHoc monHoc) {
        StringRequest request = new StringRequest(Request.Method.POST, urlAdd, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();

                myData.put("ma_mh", String.valueOf(monHoc.getMa_mh()));
                myData.put("ten_mh", String.valueOf(monHoc.getTen_mh()));
                myData.put("so_tinchi", String.valueOf(monHoc.getSo_tinchi()));
                return myData;
            }
        };
        requestQueue.add(request);
    }

    public long addRow(MonHoc monHoc){
        open();
        ContentValues values = new ContentValues();
        values.put(MonHoc.COL_MAMH,monHoc.getMa_mh());
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

    public void deleteDataOnWeb(int id) {
        StringRequest request = new StringRequest(Request.Method.POST, urlDelete, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();

                myData.put("ID", String.valueOf(id));

                return myData;
            }
        };
        requestQueue.add(request);
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

    public void updateDataToWeb(MonHoc monHoc) {
        StringRequest request = new StringRequest(Request.Method.POST, urlUpdate, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();

                myData.put("ma_mh", String.valueOf(monHoc.getMa_mh()));
                myData.put("ten_mh", String.valueOf(monHoc.getTen_mh()));
                myData.put("so_tinchi", String.valueOf(monHoc.getSo_tinchi()));

                return myData;
            }
        };
        requestQueue.add(request);
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
