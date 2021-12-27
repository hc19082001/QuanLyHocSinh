package com.example.quanlyhocsinh.StudentRelated;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.Database.DbHocSinh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HocSinhDAO {

    SQLiteDatabase database;
    DbHocSinh dbHocSinh;

    RequestQueue requestQueue ;

    String urlGetJsonStudent = "http://khang123-001-site1.dtempurl.com/Home/getJSONHocSinh";
    String urlAdd = "http://khang123-001-site1.dtempurl.com/Home/Create";
    String urlUpdate = "http://khang123-001-site1.dtempurl.com/Home/Edit";
    String urlDelete = "http://khang123-001-site1.dtempurl.com/Home/Delete";

    public HocSinhDAO(Context context) {
        dbHocSinh = new DbHocSinh(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    public void addListData(ArrayList<HocSinh> hocSinhs) {
        for (HocSinh x : hocSinhs) {
            addRow(x);
        }
    }

    public void deleteAllData() {
        open();
        database.delete(HocSinh.TB_NAME, null, null);
        close();
    }

    public void open() {
        database = dbHocSinh.getWritableDatabase();
    }

    public void close() {
        dbHocSinh.close();
    }

    //------------------------------------------

    public long addRow(HocSinh hocSinh) {

        open();
        ContentValues values = new ContentValues();

        values.put(HocSinh.COL_ID, hocSinh.getId_hs());
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

    public void addDataToWeb(HocSinh hocSinh) {


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

                myData.put("ten_hs", hocSinh.getTen_hs());
                myData.put("lop_hs", String.valueOf(hocSinh.getLop_hs()));
                myData.put("ns_hs", String.valueOf(hocSinh.getNs_hs()));

                if (hocSinh.isGioitinh_hs()) {
                    myData.put("gioitinh_hs", "1");
                } else {
                    myData.put("gioitinh_hs", "0");
                }

                myData.put("diachi_hs", hocSinh.getDiachi_hs());

                return myData;
            }
        };
        requestQueue.add(request);
    }

    public void updateDataToWeb(HocSinh hocSinh) {
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

                myData.put("id_hs", String.valueOf(hocSinh.getId_hs()) );
                myData.put("ten_hs", hocSinh.getTen_hs());
                myData.put("lop_hs", String.valueOf(hocSinh.getLop_hs()));
                myData.put("ns_hs", String.valueOf(hocSinh.getNs_hs()));

                if (hocSinh.isGioitinh_hs()) {
                    myData.put("gioitinh_hs", "1");
                } else {
                    myData.put("gioitinh_hs", "0");
                }

                myData.put("diachi_hs", hocSinh.getDiachi_hs());

                return myData;
            }
        };
        requestQueue.add(request);
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
        open();
        ArrayList<HocSinh> listHocSinhs = new ArrayList<HocSinh>();

        String [] ds_cot = new String[]{"*"};

        Cursor cursor = database.query(HocSinh.TB_NAME,ds_cot,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
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
        String[] selectionArg = new String[] {malop + ""};
        String query = "SELECT * FROM tb_hocsinh INNER JOIN Tb_Lop ON Tb_Lop.ma_lop = tb_hocsinh.lop_hs WHERE Tb_Lop.ma_lop = ?";
        Cursor cursor =  database.rawQuery(query, selectionArg);
        sl = cursor.getCount();
        cursor.moveToLast();
        return sl;
    }

    public String getTenLop(int malop) {
        open();
        String lop = null;
        String[] selectionArg = new String[] {malop + ""};
        String query = "SELECT Tb_Lop.ten_lop FROM tb_hocsinh INNER JOIN Tb_Lop ON Tb_Lop.ma_lop = tb_hocsinh.lop_hs WHERE Tb_Lop.ma_lop = ?";
        Cursor cursor =  database.rawQuery(query, selectionArg);
        cursor.moveToFirst();
        lop = cursor.getString(0);
        cursor.moveToLast();
        return lop;
    }

    public ArrayList<HocSinh> getStudentAtClass(int malop) {

        ArrayList<HocSinh> listHocSinhs = new ArrayList<>();

        String [] ds_cot = new String[]{"*"};
        String selection = HocSinh.COL_LOP + " = ? ";
        String[] Args = new String[] {malop+""};

        Cursor cursor = database.query(HocSinh.TB_NAME,ds_cot,selection,Args,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
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

    public void getDataStudentFromWeb() {

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<HocSinh> hocSinhs = new ArrayList<HocSinh>();
                int n = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        HocSinh hocSinh = new HocSinh();

                        int id = jsonObject.getInt("id_hs");
                        int malop = jsonObject.getInt("lop_hs");
                        String diachi = jsonObject.getString("diachi_hs");
                        String ten = jsonObject.getString("ten_hs");
                        int gt = jsonObject.getInt("gioitinh_hs");
                        int ns = jsonObject.getInt("ns_hs");

                        hocSinh.setId_hs(id);
                        hocSinh.setTen_hs(ten);
                        hocSinh.setLop_hs(malop);
                        hocSinh.setNs_hs(ns);
                        hocSinh.setDiachi_hs(diachi);
                        if (gt == 1) {
                            hocSinh.setGioitinh_hs(true);
                        } else {
                            hocSinh.setGioitinh_hs(false);
                        }

                        hocSinhs.add(hocSinh);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (n==0) {
                    addListData(hocSinhs);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonStudent, listener, errorListener);
        requestQueue.add(request);

    }



}
