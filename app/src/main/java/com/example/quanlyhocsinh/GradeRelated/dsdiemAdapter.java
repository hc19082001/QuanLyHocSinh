package com.example.quanlyhocsinh.GradeRelated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;


import java.util.ArrayList;

public class dsdiemAdapter extends BaseAdapter {

    DiemDAO diemDAO;
    int idhs;
    ArrayList<MonHoc> monHocs;


    public dsdiemAdapter( DiemDAO diemDAO, int idhs, ArrayList<MonHoc> monHocs ) {
        this.diemDAO = diemDAO;
        this.monHocs = monHocs;
        this.idhs = idhs;
    }

    @Override
    public int getCount() { return monHocs.size(); }

    @Override
    public Object getItem(int position) {
        return monHocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return monHocs.get(position).getMa_mh();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;

        if(convertView==null){
            itemView = View.inflate(parent.getContext(), R.layout.lv_monhoc_xemdsdiem,null);
        }else
            itemView = convertView;

        MonHoc monHoc = (MonHoc) getItem(position);                                  // Môn học hiện tại
        Diem diem = diemDAO.getDiemHS(idhs, monHocs.get(position).getMa_mh());       // Điểm môn học đó

        TextView tv_mmh = itemView.findViewById(R.id.mmh_dsd);
        TextView tv_tmh = itemView.findViewById(R.id.tmh_dsd);

        ImageButton img_btn_Xoa = itemView.findViewById(R.id.xoa_dsdiem);
        ImageButton img_btn_Sua = itemView.findViewById(R.id.sua_dsdiem);

        img_btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(parent.getContext(),position ,diem);
            }
        });

        img_btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("XÓA THÔNG TIN");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn có muốn xóa : "+ monHoc.getTen_mh());
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = diemDAO.deleteRow(diem);
                        if(kq>0){
                            diemDAO.deleteDataOnWeb(diem.getId_hs(), diem.getMa_mh());
                            monHocs.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "XÓA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(parent.getContext(), "XÓA THẤT BẠI", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        tv_mmh.setText(monHoc.getMa_mh()+"");
        tv_tmh.setText(monHoc.getTen_mh());
        return itemView;
    }

    public void showDialogUpdate(Context context, int vitri, Diem diem1) {

        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_suadiem);

        EditText dqt = dialog.findViewById(R.id.edtDiemQTSUA);
        EditText dgk = dialog.findViewById(R.id.edtDiemGKSUA);
        EditText dck = dialog.findViewById(R.id.edtDiemCKSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA_xemdsdiem);

        dqt.setText(diem1.getDiemQT() + "");
        dgk.setText(diem1.getDiemGK() + "");
        dck.setText(diem1.getDiemCK() + "");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double DQT = Double.parseDouble(dqt.getText().toString()) ;
                double DGK = Double.parseDouble(dgk.getText().toString()) ;
                double DCK = Double.parseDouble(dck.getText().toString()) ;

                diem1.setDiemQT(DQT);
                diem1.setDiemGK(DGK);
                diem1.setDiemCK(DCK);

                int kq = diemDAO.updateRow(diem1);
                if(kq>0){
                    diemDAO.updateDataToWeb(diem1);
                    Toast.makeText(context, "SỬA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "SỬA THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
