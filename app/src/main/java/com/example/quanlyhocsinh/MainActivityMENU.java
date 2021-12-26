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

import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.GradeRelated.MainActivityDiem;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;
import com.google.android.material.navigation.NavigationView;

public class MainActivityMENU extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;


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