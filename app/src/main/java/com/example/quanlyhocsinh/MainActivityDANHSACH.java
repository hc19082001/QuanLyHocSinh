package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivityDANHSACH extends AppCompatActivity {

    Button btnTHEMTIEP;
    ListView lv_danhsach;
    HocSinhDAO hocSinhDAO;
    HocSinhAdapter hocSinhAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_danhsach);

        btnTHEMTIEP = findViewById(R.id.btnTHEMTIEP);
        lv_danhsach = findViewById(R.id.lv_danhsach);

        hocSinhDAO = new HocSinhDAO(MainActivityDANHSACH.this);
        hocSinhDAO.open();

        hocSinhAdapter = new HocSinhAdapter(hocSinhDAO.getAll(),hocSinhDAO);
        lv_danhsach.setAdapter(hocSinhAdapter);

        btnTHEMTIEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             hocSinhAdapter.showDialogAdd(MainActivityDANHSACH.this);
            }
        });
    }
}