package com.example.quanlyhocsinh.ClassRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityTHEM_MONHOC;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;
import androidx.appcompat.widget.Toolbar;

public class MainActivityTHEM_LOP extends AppCompatActivity {

    EditText edt_ten;
    EditText edt_slg;

    Button btnSave;
    LopDAO lopDAO;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_lop);

        edt_ten = findViewById(R.id.edt_tenADD_lop);
        edt_slg = findViewById(R.id.edt_slADD_lop);
        btnSave = findViewById(R.id.btnLUUADD_lop);

        lopDAO = new LopDAO(MainActivityTHEM_LOP.this);

        toolbar = findViewById(R.id.toolbar_themLop);

        setSupportActionBar(toolbar);//thay thế actionbar thông thường
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Set thanh điều hướng cho toolbar

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();
                int slg = Integer.parseInt(edt_slg.getText().toString());

                Lop lop = new Lop(ten, slg);

                long kq = lopDAO.addRow(lop);

                if(kq>0){
                    Toast.makeText(MainActivityTHEM_LOP.this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityTHEM_LOP.this, MainActivityLop.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivityTHEM_LOP.this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
