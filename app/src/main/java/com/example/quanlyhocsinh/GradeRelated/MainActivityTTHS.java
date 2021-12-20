package com.example.quanlyhocsinh.GradeRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.StudentRelated.HocSinhDAO;
import com.example.quanlyhocsinh.StudentRelated.MainActivityDANHSACH;
import com.example.quanlyhocsinh.StudentRelated.MainActivityTHEM_HocSinh;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;
import com.example.quanlyhocsinh.SubjectRelated.MonHocDAO;

import java.util.ArrayList;

public class MainActivityTTHS extends AppCompatActivity {

    Toolbar toolbar;
    TextView textViewID, textViewLop, textViewTen;
    ImageView imageViewGT;
    ListView listView;
    HocSinhDAO hocSinhDAO;
    MonHocDAO monHocDAO;
    DiemDAO diemDAO;
    dsdiemAdapter adapter;
    ArrayList<MonHoc> monHocs;
    int idhs, lop;
    String ten;
    boolean gt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_monhochs);

        toolbar = findViewById(R.id.toolbar_TTHS);
        textViewID = findViewById(R.id.id_hs_dsdiem);
        textViewLop = findViewById(R.id.lop_hs_dsdiem);
        textViewTen = findViewById(R.id.ten_hs_dsdiem);
        imageViewGT = findViewById(R.id.imv_hs_dsdiem);
        listView = findViewById(R.id.lv_hs_xemdsdiem);
        hocSinhDAO = new HocSinhDAO(this);
        monHocDAO = new MonHocDAO(this);
        diemDAO = new DiemDAO(this);

        Bundle bundle = getIntent().getExtras();

        idhs = bundle.getInt("idhs");
        ten = bundle.getString("tenhs");
        lop = bundle.getInt("lophs");
        gt = bundle.getBoolean("gths");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewID.setText( idhs +"");
        textViewTen.setText(ten);
        textViewLop.setText(hocSinhDAO.getTenLop(lop));

        if (gt) {
            imageViewGT.setImageResource(R.drawable.ic_baseline_boy_24);
        } else {
            imageViewGT.setImageResource(R.drawable.ic_baseline_local_florist_24);
        }

        monHocs = monHocDAO.getMonHocOf(idhs);

        adapter = new dsdiemAdapter(diemDAO, idhs, monHocs);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MonHoc monHoc = monHocs.get(position);
                Diem diem = diemDAO.getDiemHS(idhs, monHoc.getMa_mh());

                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Thông tin học điểm học sinh");
                builder.setIcon(R.drawable.ic_baseline_remove_red_eye_24);

                builder.setMessage("Tên môn học : "+ monHoc.getTen_mh()+
                        "\nĐiểm quá trình : "+ diem.getDiemQT()+
                        "\nĐiểm giữa kỳ : "+diem.getDiemGK()+
                        "\nĐiểm cuối kỳ : "+diem.getDiemCK());
                builder.setPositiveButton("THOÁT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

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
                Intent intent = new Intent(MainActivityTTHS.this, MainActivityTHEMDIEM.class);
                intent.putExtra("idhs2", idhs);
                intent.putExtra("tenhs2", ten);
                intent.putExtra("lophs2", lop);
                intent.putExtra("gt2", gt);
                startActivity(intent);
                break;
            default:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}