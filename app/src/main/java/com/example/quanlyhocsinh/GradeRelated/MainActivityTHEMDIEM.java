package com.example.quanlyhocsinh.GradeRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityMonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MainActivityTHEM_MONHOC;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivityTHEMDIEM extends AppCompatActivity {

    Spinner spinner;
    EditText editTextDQT, editTextDGK, editTextDCK;
    Button button;
    ArrayList<MonHoc> monHocs;
    MonHocDAO monHocDAO;
    DiemDAO diemDAO;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_themdsdiemhs);

        spinner = findViewById(R.id.spinner_themmonhoc_xemdsdiem);
        editTextDQT = findViewById(R.id.edtDiemQTTHEM);
        editTextDGK = findViewById(R.id.edtDiemGKTHEM);
        editTextDCK = findViewById(R.id.edtDiemCKTHEM);
        button = findViewById(R.id.btnTHEM_xemdsdiem);
        diemDAO = new DiemDAO(this);

        toolbar = findViewById(R.id.toolbar_themdiem);

        Bundle bundle = getIntent().getExtras();
        int idhs = bundle.getInt("idhs2");
        int lop = bundle.getInt("lophs2");
        String ten = bundle.getString("tenhs2");
        boolean gt = bundle.getBoolean("gt2");

        monHocDAO = new MonHocDAO(this);
        monHocs = monHocDAO.getAll();

        setSupportActionBar(toolbar);//thay thế actionbar thông thường
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Set thanh điều hướng cho toolbar

        ArrayAdapter<MonHoc> monHocArrayAdapter = new ArrayAdapter<MonHoc>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                monHocs
        );
        spinner.setAdapter(monHocArrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                MonHoc monHoc = (MonHoc) spinner.getSelectedItem();

                int dqt = Integer.parseInt(editTextDQT.getText().toString());
                int dgk = Integer.parseInt(editTextDGK.getText().toString());
                int dck = Integer.parseInt(editTextDCK.getText().toString());

                Diem diem = new Diem(idhs, monHoc.getMa_mh(), dqt, dgk, dck);

                long kq = diemDAO.addRow(diem);

                if(kq>0){
                    Toast.makeText(MainActivityTHEMDIEM.this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivityTHEMDIEM.this, MainActivityTTHS.class);
                    intent.putExtra("idhs", idhs);
                    intent.putExtra("tenhs", ten);
                    intent.putExtra("lophs", lop);
                    intent.putExtra("gths", gt);

                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivityTHEMDIEM.this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
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