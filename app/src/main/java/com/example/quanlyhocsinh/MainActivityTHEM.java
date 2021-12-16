package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivityTHEM extends AppCompatActivity {

    EditText edt_ten;
    EditText edt_lop;
    EditText edt_ns;
    RadioGroup radioGroup_GT;
    RadioButton radioButton_Nam;
    RadioButton radioButton_Nu;
    EditText edt_diachi;
    Button btnClear;
    Button btnSave;
    HocSinhDAO hocSinhDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them);

        edt_ten = findViewById(R.id.edt_tenADD);
        edt_lop = findViewById(R.id.edt_lopADD);
        edt_ns = findViewById(R.id.edt_namsinhADD);
        edt_diachi = findViewById(R.id.edt_diachi);
        radioGroup_GT = findViewById(R.id.radioGroupNamNu);
        radioButton_Nam = findViewById(R.id.rbNam);
        radioButton_Nu = findViewById(R.id.rbNu);

        btnClear = findViewById(R.id.btnCLEARADD);
        btnSave = findViewById(R.id.btnLUUADD);

        hocSinhDAO = new HocSinhDAO(MainActivityTHEM.this);
        hocSinhDAO.open();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HocSinh hocSinh = new HocSinh();
                String ten = edt_ten.getText().toString();
                String lop = edt_lop.getText().toString();
                int ns = Integer.parseInt(edt_ns.getText().toString());
                boolean gt = false;
                if (radioGroup_GT.getCheckedRadioButtonId() == radioButton_Nam.getId()) {
                    gt = true;
                }
                String dc = edt_diachi.getText().toString();

                hocSinh.setTen_hs(ten);
                hocSinh.setTen_hs(ten);
                hocSinh.setLop_hs(lop);
                hocSinh.setNs_hs(ns);
                hocSinh.setGioitinh_hs(gt);
                hocSinh.setDiachi_hs(dc);
                long kq = hocSinhDAO.addRow(hocSinh);

                if(kq>0){
                    Toast.makeText(MainActivityTHEM.this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityTHEM.this,MainActivityDANHSACH.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivityTHEM.this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}