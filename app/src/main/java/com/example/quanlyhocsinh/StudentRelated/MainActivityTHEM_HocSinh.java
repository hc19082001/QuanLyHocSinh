package com.example.quanlyhocsinh.StudentRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.R;

import java.util.ArrayList;

public class MainActivityTHEM_HocSinh extends AppCompatActivity {

    EditText edt_ten;
    Spinner spinner_addlop;
    EditText edt_ns;
    RadioGroup radioGroup_GT;
    RadioButton radioButton_Nam;
    RadioButton radioButton_Nu;
    EditText edt_diachi;
    Button btnClear;
    Button btnSave;
    HocSinhDAO hocSinhDAO;
    LopDAO lopDAO;
    ArrayList<Lop> lopArrayList;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them);

        edt_ten = findViewById(R.id.edt_tenADD);
        spinner_addlop = findViewById(R.id.spinner_lop);
        edt_ns = findViewById(R.id.edt_namsinhADD);
        edt_diachi = findViewById(R.id.edt_diachi);
        radioGroup_GT = findViewById(R.id.radioGroupNamNu);
        radioButton_Nam = findViewById(R.id.rbNam);
        radioButton_Nu = findViewById(R.id.rbNu);
        toolbar = findViewById(R.id.toolbar_them_hs);

        btnClear = findViewById(R.id.btnCLEARADD);
        btnSave = findViewById(R.id.btnLUUADD);

        hocSinhDAO = new HocSinhDAO(MainActivityTHEM_HocSinh.this);
        lopDAO = new LopDAO(MainActivityTHEM_HocSinh.this);
        lopArrayList = lopDAO.getAll();

        setSupportActionBar(toolbar);//thay thế actionbar thông thường
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Set thanh điều hướng cho toolbar


        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<Lop>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                lopArrayList
        );

        spinner_addlop.setAdapter(lopArrayAdapter);


        hocSinhDAO.open();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HocSinh hocSinh = new HocSinh();
                String ten = edt_ten.getText().toString();

                Lop lop = (Lop) spinner_addlop.getSelectedItem();
                int malop = lop.getMa_lop();
                int ns = Integer.parseInt(edt_ns.getText().toString());
                boolean gt = false;
                if (radioGroup_GT.getCheckedRadioButtonId() == radioButton_Nam.getId()) {
                    gt = true;
                }
                String dc = edt_diachi.getText().toString();

                Lop lop1 = layObjLop(malop);
                int soluongHSLop = lop1.getSo_luong();
                int soluongHSHienTai = hocSinhDAO.getSLHSmotLop(malop);
                if (soluongHSHienTai >= soluongHSLop) {
                    Toast.makeText(MainActivityTHEM_HocSinh.this, "SỐ LƯỢNG HỌC SINH LỚP HIỆN TẠI ĐÃ ĐỦ, MỜI CHỌN LỚP KHÁC", Toast.LENGTH_SHORT).show();
                } else {
                    hocSinh.setTen_hs(ten);
                    hocSinh.setTen_hs(ten);
                    hocSinh.setLop_hs(malop);
                    hocSinh.setNs_hs(ns);
                    hocSinh.setGioitinh_hs(gt);
                    hocSinh.setDiachi_hs(dc);
                    hocSinhDAO.addRow(hocSinh);
                    hocSinhDAO.addDataToWeb(hocSinh);

                    Toast.makeText(MainActivityTHEM_HocSinh.this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityTHEM_HocSinh.this,MainActivityDANHSACH.class);
                    startActivity(intent);

                }



            }


        });
    }


    private Lop layObjLop(int malop) {

        for (Lop x :
                lopArrayList) {
            if (x.getMa_lop() == malop) {
                return x;
            }
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}