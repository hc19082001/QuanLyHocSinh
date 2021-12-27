package com.example.quanlyhocsinh.ClassRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LopDAO {

    DbHocSinh dbHocSinh;
    SQLiteDatabase sqLiteDatabase;

    RequestQueue requestQueue;

    String urlGetJsonClass = "http://khang123-001-site1.dtempurl.com/QuanLyLop/getJSONLop";
    String urlAdd = "http://khang123-001-site1.dtempurl.com/QuanLyLop/Create";
    String urlUpdate = "http://khang123-001-site1.dtempurl.com/QuanLyLop/Edit";
    String urlDelete = "http://khang123-001-site1.dtempurl.com/QuanLyLop/Delete";

    public LopDAO(Context context) {

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
        sqLiteDatabase.delete(Lop.DB_LOP_NAME, null, null);
        close();
    }


    public void addListData(ArrayList<Lop> lops) {
        for (Lop x: lops) {
            addRow(x);
        }
    }




    public long addRow(Lop lop){
        open();
        ContentValues values = new ContentValues();
        values.put(Lop.COL_MALOP, lop.getMa_lop());
        values.put(Lop.COL_TENLOP, lop.getTen_lop());
        values.put(Lop.COL_SOLUONG, lop.getSo_luong());

        long res = sqLiteDatabase.insert(Lop.DB_LOP_NAME, null, values);
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

    public void addDataFromWeb(Lop lop){
       StringRequest request = new StringRequest(Request.Method.POST, urlAdd, new Response.Listener<String>() {
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
               myData.put("ma_lop", String.valueOf( lop.getMa_lop()));
               myData.put("ten_lop", lop.getTen_lop());
               myData.put("so_luong",String.valueOf(lop.getSo_luong()));
               return myData;
           }
       };
       requestQueue.add(request);
    }

    public void updateDataToWeb(Lop lop){

        StringRequest request = new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> myData = new HashMap<>();
                myData.put("ma_lop", String.valueOf(lop.getMa_lop()));
                myData.put("ten_lop", lop.getTen_lop());
                myData.put("so_luong", String.valueOf(lop.getSo_luong()));

                return myData;
            }
        };
        requestQueue.add(request);
    }

    public void deleteDataToWeb(int id){
        StringRequest request = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();
                myData.put("ID",String.valueOf(id));
                return myData;
            }
        };
        requestQueue.add(request);
    }

    public void getDataClassFromWeb() {

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Lop> lops = new ArrayList<Lop>();
                int n = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Lop lop = new Lop();

                        int malop = jsonObject.getInt("ma_lop");
                        String tenlop = jsonObject.getString("ten_lop");
                        int solg = jsonObject.getInt("so_luong");

                        lop.setMa_lop(malop);
                        lop.setTen_lop(tenlop);
                        lop.setSo_luong(solg);

                        lops.add(lop);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (n==0) {
                    addListData(lops);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonClass, listener, errorListener);
        requestQueue.add(request);

    }




}
