package com.example.quanlyhocsinh.StudentRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.ClassRelated.MainActivityTHEM_LOP;
import com.example.quanlyhocsinh.MainActivity;
import com.example.quanlyhocsinh.MainActivityMENU;
import com.example.quanlyhocsinh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityDANHSACH extends AppCompatActivity {

    String urlGetJSON = "http://khang123-001-site1.dtempurl.com/Home/getJSON";

    ListView lv_danhsach;
    HocSinhDAO hocSinhDAO;
    LopDAO lopDAO;
    HocSinhAdapter hocSinhAdapter;
    Toolbar toolbar;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsach);

        lv_danhsach = findViewById(R.id.lv_danhsach);
        toolbar = findViewById(R.id.toolbar);
        requestQueue = Volley.newRequestQueue(this);

        hocSinhDAO = new HocSinhDAO(this);
        lopDAO = new LopDAO(MainActivityDANHSACH.this);

        hocSinhDAO.open();

        hocSinhDAO.deleteAllData();
        getDataFromWeb();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void getDataFromWeb() {

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
                hocSinhAdapter = new HocSinhAdapter(hocSinhs,hocSinhDAO, lopDAO);
                lv_danhsach.setAdapter(hocSinhAdapter);
                if (n==0) {
                    hocSinhDAO.addListData(hocSinhs);
                    n++;
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityDANHSACH.this, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(urlGetJSON, listener, errorListener);
        requestQueue.add(request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addIT:
                Intent intent = new Intent(MainActivityDANHSACH.this, MainActivityTHEM_HocSinh.class);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(MainActivityDANHSACH.this, MainActivityMENU.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}