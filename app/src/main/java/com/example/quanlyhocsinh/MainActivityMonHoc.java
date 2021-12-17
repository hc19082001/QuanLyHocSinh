package com.example.quanlyhocsinh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivityMonHoc extends AppCompatActivity {

    ListView lv_danhsach;
    MonHocDAO monHocDAO;
    MonHocAdapter monHocAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mon_hoc);

        lv_danhsach = findViewById(R.id.lv_danhsach_mh);
        toolbar = findViewById(R.id.toolbar_mh);

        monHocDAO = new MonHocDAO(MainActivityMonHoc.this);

        monHocAdapter = new MonHocAdapter(monHocDAO.getAll(), monHocDAO);
        lv_danhsach.setAdapter(monHocAdapter);

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
                Intent intent = new Intent(MainActivityMonHoc.this, MainActivityTHEM_MONHOC.class);
                startActivity(intent);
                break;
            default:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}