package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityMENU extends AppCompatActivity {

    Button btnThem;
    Button btnDanhsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnThem = findViewById(R.id.btnTHEM);
        btnDanhsach = findViewById(R.id.btnDANHSACH);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMENU.this,MainActivityTHEM.class);
                startActivity(intent);
            }
        });

        btnDanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMENU.this,MainActivityDANHSACH.class);
                startActivity(intent);
            }
        });
    }
}