package com.example.quanlyhocsinh.ClassRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.FloatProperty;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.MainActivityMENU;
import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityTHEM_MONHOC;
import com.example.quanlyhocsinh.SubjectRelated.MonHocAdapter;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;

import java.util.ArrayList;

public class MainActivityLop extends AppCompatActivity {

    ListView lv_danhsach;
    LopDAO lopDAO;
    LopAdapter lopAdapter;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lop);

        lv_danhsach = findViewById(R.id.lv_danhsach_lop);
        toolbar = findViewById(R.id.toolbar_lop);

        lopDAO = new LopDAO(MainActivityLop.this);

        lopAdapter = new LopAdapter( lopDAO.getAll(), lopDAO) ;
        lv_danhsach.setAdapter(lopAdapter);

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
                Intent intent = new Intent(MainActivityLop.this, MainActivityTHEM_LOP.class);
                startActivity(intent);
                break;

            default:
                Intent intent1 = new Intent(MainActivityLop.this, MainActivityMENU.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}