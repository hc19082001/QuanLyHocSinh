package com.example.quanlyhocsinh.StudentRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.ClassRelated.MainActivityTHEM_LOP;
import com.example.quanlyhocsinh.MainActivity;
import com.example.quanlyhocsinh.MainActivityMENU;
import com.example.quanlyhocsinh.R;

public class  MainActivityDANHSACH extends AppCompatActivity {
    
    ListView lv_danhsach;
    HocSinhDAO hocSinhDAO;
    LopDAO lopDAO;
    HocSinhAdapter hocSinhAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsach);

        lv_danhsach = findViewById(R.id.lv_danhsach);
        toolbar = findViewById(R.id.toolbar);

        hocSinhDAO = new HocSinhDAO(MainActivityDANHSACH.this);
        lopDAO = new LopDAO(MainActivityDANHSACH.this);
        hocSinhDAO.open();

        hocSinhAdapter = new HocSinhAdapter(hocSinhDAO.getAll(),hocSinhDAO, lopDAO);
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