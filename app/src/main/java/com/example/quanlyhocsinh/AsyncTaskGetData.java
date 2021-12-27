package com.example.quanlyhocsinh;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.GradeRelated.Diem;
import com.example.quanlyhocsinh.GradeRelated.DiemDAO;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinhDAO;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AsyncTaskGetData extends AsyncTask<Void, Void, Void> {

    final String urlGetJsonStudent = "http://khang123-001-site1.dtempurl.com/Home/getJSONHocSinh";
    final String urlGetJsonGrade = "http://khang123-001-site1.dtempurl.com/QuanLyDiem/getJSONDiem";
    final String urlGetJsonSubject = "http://khang123-001-site1.dtempurl.com/QuanLyMonHoc/getJSONMonHoc";
    final String urlGetJsonClass = "http://khang123-001-site1.dtempurl.com/QuanLyLop/getJSONLop";

    Context context;

    HocSinhDAO hocSinhDAO;
    LopDAO lopDAO;
    DiemDAO diemDAO;
    MonHocDAO monHocDAO;

    RequestQueue requestQueue;
    Dialog dialog;

    public AsyncTaskGetData(Context context) {
        this.context = context;
        lopDAO = new LopDAO(context);
        diemDAO = new DiemDAO(context);
        hocSinhDAO = new HocSinhDAO(context);
        monHocDAO = new MonHocDAO(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.loadingdata);

        dialog.show();

    }

    @Override
    protected Void doInBackground(Void... voids) {

        deleteAllData();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getAllDataFromWeb();

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        dialog.dismiss();
    }

    private void getAllDataFromWeb() {

        getDataStudentFromWeb();
        getDataGradeFromWeb();
        getDataClassFromWeb();
        getDataSubjectFromWeb();

    }

    private void getDataGradeFromWeb() {

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Diem> diems = new ArrayList<Diem>();
                int n = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Diem diem = new Diem();

                        int idhs = jsonObject.getInt("id_hs");
                        int mamh = jsonObject.getInt("ma_mh");
                        double diemQT = jsonObject.getDouble("diemQT");
                        double diemGK = jsonObject.getDouble("diemGK");
                        double diemCK = jsonObject.getDouble("diemCK");

                        diem.setId_hs(idhs);
                        diem.setMa_mh(mamh);
                        diem.setDiemQT(diemQT);
                        diem.setDiemGK(diemGK);
                        diem.setDiemCK(diemCK);

                        diems.add(diem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (n==0) {
                    diemDAO.addListData(diems);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonGrade, listener, errorListener);
        requestQueue.add(request);

    }

    private void getDataSubjectFromWeb() {

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<MonHoc> monHocs = new ArrayList<MonHoc>();
                int n = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        MonHoc monHoc = new MonHoc();

                        int mamh = jsonObject.getInt("ma_mh");
                        String tenmh = jsonObject.getString("ten_mh");
                        int sotc = jsonObject.getInt("so_tinchi");

                        monHoc.setMa_mh(mamh);
                        monHoc.setTen_mh(tenmh);
                        monHoc.setSo_tinchi(sotc);

                        monHocs.add(monHoc);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (n==0) {
                    monHocDAO.addListData(monHocs);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonSubject, listener, errorListener);
        requestQueue.add(request);

    }

    private void getDataClassFromWeb() {

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
                    lopDAO.addListData(lops);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonClass, listener, errorListener);
        requestQueue.add(request);

    }

    private void getDataStudentFromWeb() {

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
                    hocSinhDAO.addListData(hocSinhs);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJsonStudent, listener, errorListener);
        requestQueue.add(request);

    }

    private void deleteAllData() {

        hocSinhDAO.deleteAllData();
        lopDAO.deleteAllData();
        diemDAO.deleteAllData();
        monHocDAO.deleteAllData();

    }

}
