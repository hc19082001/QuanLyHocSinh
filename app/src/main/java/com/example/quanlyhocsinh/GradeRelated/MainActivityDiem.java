package com.example.quanlyhocsinh.GradeRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.ClassRelated.MainActivityLop;
import com.example.quanlyhocsinh.ClassRelated.MainActivityTHEM_LOP;
import com.example.quanlyhocsinh.MainActivity;
import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinhDAO;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;

import java.util.ArrayList;

public class MainActivityDiem extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    ListView listView;
    LopDAO lopDAO;
    ArrayList<Lop> lopArrayListSn;
    DiemAdapter diemAdapter;
    HocSinhDAO hocSinhDAO;
    ArrayList<HocSinh> hocSinhArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_diem);

        spinner = findViewById(R.id.spinner_diemhs);
        toolbar = findViewById(R.id.toolbar_diem);
        listView = findViewById(R.id.lv_diem);

        hocSinhDAO = new HocSinhDAO(MainActivityDiem.this);
        hocSinhArrayList = hocSinhDAO.getAll();

        lopDAO = new LopDAO(this);
        lopArrayListSn = lopDAO.getAll();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<Lop>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                lopArrayListSn
        );
        spinner.setAdapter(lopArrayAdapter);

        diemAdapter = new DiemAdapter(hocSinhDAO, hocSinhArrayList);
        listView.setAdapter(diemAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Lop lop = lopArrayListSn.get(position);
                hocSinhArrayList = hocSinhDAO.getStudentAtClass(lop.getMa_lop());
                diemAdapter = new DiemAdapter(hocSinhDAO, hocSinhArrayList);
                listView.setAdapter(diemAdapter);
                diemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HocSinh hocSinh = (HocSinh) diemAdapter.getItem(position);

                Intent intent = new Intent(MainActivityDiem.this, MainActivityTTHS.class);

                intent.putExtra("idhs", hocSinh.getId_hs());
                intent.putExtra("tenhs", hocSinh.getTen_hs());
                intent.putExtra("lophs", hocSinh.getLop_hs());
                intent.putExtra("gths", hocSinh.isGioitinh_hs());

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


}