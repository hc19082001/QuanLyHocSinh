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

        hocSinhDAO.getDataStudentFromWeb();
        monHocDAO.getDataSubjectFromWeb();
        diemDAO.getDataGradeFromWeb();
        lopDAO.getDataClassFromWeb();

    }

    private void deleteAllData() {

        hocSinhDAO.deleteAllData();
        lopDAO.deleteAllData();
        diemDAO.deleteAllData();
        monHocDAO.deleteAllData();

    }

}
