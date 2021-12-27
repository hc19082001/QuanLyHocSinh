package com.example.quanlyhocsinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar; // Dùng cho setSupportActionBar(toolbar);

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.Database.DbHocSinh;
import com.example.quanlyhocsinh.GradeRelated.Diem;
import com.example.quanlyhocsinh.GradeRelated.DiemDAO;
import com.example.quanlyhocsinh.GradeRelated.MainActivityDiem;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinhAdapter;
import com.example.quanlyhocsinh.StudentRelated.HocSinhDAO;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityMENU extends AppCompatActivity {

    final String urlGetJsonStudent = "http://khang123-001-site1.dtempurl.com/Home/getJSONHocSinh";
    final String urlGetJsonGrade = "http://khang123-001-site1.dtempurl.com/QuanLyDiem/getJSONDiem";
    final String urlGetJsonSubject = "http://khang123-001-site1.dtempurl.com/QuanLyMonHoc/getJSONMonHoc";
    final String urlGetJsonClass = "http://khang123-001-site1.dtempurl.com/QuanLyLop/getJSONLop";

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    AsyncTaskGetData asyncTaskGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar_nav);

        NavigationView navigationView = findViewById(R.id.nav_drawer);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        asyncTaskGetData = new AsyncTaskGetData(MainActivityMENU.this);
        asyncTaskGetData.execute();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.qltt:
                        Intent intent = new Intent(MainActivityMENU.this, MainActivityDANHSACH.class);
                        startActivity(intent);
                        break;
                    case  R.id.qld:
                        Intent intent1 = new Intent(MainActivityMENU.this, MainActivityDiem.class);
                        startActivity(intent1);
                        break;
                    case  R.id.qll:
                        Intent intent2 = new Intent(MainActivityMENU.this, MainActivityLop.class);
                        startActivity(intent2);break;
                    case  R.id.qlmh:
                        Intent intent3 = new Intent(MainActivityMENU.this, MainActivityMonHoc.class);
                        startActivity(intent3);
                        break;
                    default:
                       onBackPressed();
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else  {
            super.onBackPressed();
        }
    }
}