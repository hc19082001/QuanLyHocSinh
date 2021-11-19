package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityTHEM extends AppCompatActivity {

    EditText edt_ten;
    EditText edt_lop;
    EditText edt_tuoi;
    EditText edt_gt;
    Button btnClear;
    Button btnSave;
    HocSinhDAO hocSinhDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them);

        edt_ten = findViewById(R.id.edt_tenADD);
        edt_lop = findViewById(R.id.edt_lopADD);
        edt_tuoi = findViewById(R.id.edt_tuoiADD);
        edt_gt = findViewById(R.id.edt_gioitinhADD);

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
                int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
                String gioitinh = edt_gt.getText().toString();

                hocSinh.setTen_hs(ten);
                hocSinh.setLop_hs(lop);
                hocSinh.setTuoi_hs(tuoi);
                hocSinh.setGioitinh_hs(gioitinh);

                long kq = hocSinhDAO.addRow(hocSinh);

                if(kq>0){
                    Intent intent = new Intent(MainActivityTHEM.this,MainActivityMENU.class);
                    startActivity(intent);

                    Toast.makeText(MainActivityTHEM.this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivityTHEM.this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               edt_ten.setText("");
               edt_lop.setText("");
               edt_tuoi.setText("");
               edt_gt.setText("");
            }
        });



    }
}