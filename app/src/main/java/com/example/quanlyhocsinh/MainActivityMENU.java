package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;

public class MainActivityMENU extends AppCompatActivity {


    Button btnDanhsach;
    Button btnMH;
    Button btnLOP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnDanhsach = findViewById(R.id.btnDANHSACH);
        btnMH = findViewById(R.id.btn_MonHoc);
        btnLOP = findViewById(R.id.btn_LOP);

        btnDanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMENU.this, MainActivityDANHSACH.class);
                startActivity(intent);
            }
        });

        btnMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMENU.this, MainActivityMonHoc.class);
                startActivity(intent);
            }
        });

        btnLOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMENU.this, MainActivityLop.class);
                startActivity(intent);
            }
        });
    }
}