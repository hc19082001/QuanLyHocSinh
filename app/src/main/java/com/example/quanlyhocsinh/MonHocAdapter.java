package com.example.quanlyhocsinh;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonHocAdapter extends BaseAdapter {

    ArrayList<MonHoc> monHocs;
    MonHocDAO monHocDAO;

    public MonHocAdapter(ArrayList<MonHoc> monHocs, MonHocDAO monHocDAO) {
        this.monHocs = monHocs;
        this.monHocDAO = monHocDAO;
    }

    @Override
    public int getCount() {
        return monHocs.size();
    }

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
            itemView = View.inflate(parent.getContext(),R.layout.item_listview_monhoc,null);
        }else
            itemView = convertView;

        MonHoc monHoc = (MonHoc) getItem(position);

        TextView tv_id = itemView.findViewById(R.id.tv_id_mh);
        TextView tv_ten = itemView.findViewById(R.id.tv_ten_mh);
        TextView tv_lop = itemView.findViewById(R.id.tv_stc);

        ImageButton img_btn_Sua = itemView.findViewById(R.id.imgbtnSua_mh);
        ImageButton img_btn_Xoa = itemView.findViewById(R.id.imgbtnXoa_mh);

        img_btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(parent.getContext(),position ,monHoc);
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
                        int kq = monHocDAO.deleteRow(monHoc);
                        if(kq>0){
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



        tv_id.setText(monHoc.getMa_mh()+"");
        tv_ten.setText(monHoc.getTen_mh());
        tv_lop.setText(monHoc.getSo_tinchi()+"");

        return itemView;
    }

    public void showDialogUpdate(Context context, int vitri, MonHoc monHoc){

        final Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_mh);

        TextView tv_id = dialog.findViewById(R.id.tvIDSUA_mh);
        EditText edt_ten = dialog.findViewById(R.id.edtTenSUA_mh);
        EditText edt_stc = dialog.findViewById(R.id.edtSoTCSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA_mh);

        tv_id.setText(monHoc.getMa_mh()+"");
        edt_ten.setText(monHoc.getTen_mh());
        edt_stc.setText(monHoc.getSo_tinchi()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();
                int stc = Integer.parseInt(edt_stc.getText().toString()) ;

                monHoc.setTen_mh(ten);
                monHoc.setSo_tinchi(stc);

                int kq = monHocDAO.updateRow(monHoc);
                if(kq>0){
                    monHocs.set(vitri,monHoc);
                    notifyDataSetChanged();
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
