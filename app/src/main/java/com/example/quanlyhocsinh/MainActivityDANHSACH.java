package com.example.quanlyhocsinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivityDANHSACH extends AppCompatActivity {
    
    ListView lv_danhsach;
    HocSinhDAO hocSinhDAO;
    HocSinhAdapter hocSinhAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsach);

        lv_danhsach = findViewById(R.id.lv_danhsach);
        toolbar = findViewById(R.id.toolbar);

        hocSinhDAO = new HocSinhDAO(MainActivityDANHSACH.this);
        hocSinhDAO.open();

        hocSinhAdapter = new HocSinhAdapter(hocSinhDAO.getAll(),hocSinhDAO);
        lv_danhsach.setAdapter(hocSinhAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Intent intent = new Intent(MainActivityDANHSACH.this, MainActivityTHEM.class);
                startActivity(intent);
                break;
            default:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}