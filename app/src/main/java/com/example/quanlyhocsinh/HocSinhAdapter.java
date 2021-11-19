package com.example.quanlyhocsinh;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HocSinhAdapter extends BaseAdapter {

    ArrayList<HocSinh> listHocSinhs;
    HocSinhDAO hocSinhDAO;

    public HocSinhAdapter(ArrayList<HocSinh> listHocSinhs, HocSinhDAO hocSinhDAO) {
        this.listHocSinhs = listHocSinhs;
        this.hocSinhDAO = hocSinhDAO;
    }

    @Override
    public int getCount() {
        return listHocSinhs.size();
    }

    @Override
    public Object getItem(int position) {
        return listHocSinhs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listHocSinhs.get(position).getId_hs();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemView;

        if(view==null){
            itemView = View.inflate(viewGroup.getContext(),R.layout.item_listview_hocsinh,null);
        }else
            itemView = view;

        HocSinh hocSinh = listHocSinhs.get(position);

        TextView tv_id = itemView.findViewById(R.id.tv_id);
        TextView tv_ten = itemView.findViewById(R.id.tv_ten);
        TextView tv_lop = itemView.findViewById(R.id.tv_lop);
        TextView tv_tuoi = itemView.findViewById(R.id.tv_tuoi);
        TextView tv_gioitinh = itemView.findViewById(R.id.tv_gioitinh);
        TextView tv_sua = itemView.findViewById(R.id.tv_sua);
        TextView tv_xoa = itemView.findViewById(R.id.tv_xoa);

        tv_ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Thông tin học sinh");
                builder.setIcon(android.R.drawable.ic_input_add);
                builder.setMessage("ID : "+hocSinh.getId_hs()+
                        "\nTên : "+hocSinh.getTen_hs()+
                        "\nLớp : "+hocSinh.getLop_hs()+
                        "\nTuổi : "+hocSinh.getTuoi_hs()+
                        "\ngiới tính : "+hocSinh.getTuoi_hs());
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
        tv_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(viewGroup.getContext(),position ,hocSinh);
            }
        });
        tv_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("XÓA THÔNG TIN");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn có muốn xóa : "+hocSinh.getTen_hs());
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = hocSinhDAO.deleteRow(hocSinh);
                        if(kq>0){
                            listHocSinhs.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "XÓA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(viewGroup.getContext(), "XÓA THẤT BẠI", Toast.LENGTH_SHORT).show();
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

        tv_id.setText(hocSinh.getId_hs()+"");
        tv_ten.setText(hocSinh.getTen_hs());
        tv_lop.setText(hocSinh.getLop_hs());
        tv_tuoi.setText(hocSinh.getTuoi_hs()+"");
        tv_gioitinh.setText(hocSinh.getGioitinh_hs());

        return itemView;
    }

    public void showDialogAdd(Context context){
        final Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_them_tiep);

        EditText edt_ten = dialog.findViewById(R.id.edtTenTHEMTIEP);
        EditText edt_lop = dialog.findViewById(R.id.edtLopTHEMTIEP);
        EditText edt_tuoi = dialog.findViewById(R.id.edtTuoiTHEMTIEP);
        EditText edt_gioitinh = dialog.findViewById(R.id.edtGioiTinhTHEMTIEP);

        Button btnClear = dialog.findViewById(R.id.btnClearTHEMTIEP);
        Button btnSave = dialog.findViewById(R.id.btnLuuTHEMTIEP);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HocSinh hocSinh = new HocSinh();
                String ten = edt_ten.getText().toString();
                String lop = edt_lop.getText().toString();
                int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
                String gioitinh = edt_gioitinh.getText().toString();

                hocSinh.setTen_hs(ten);
                hocSinh.setLop_hs(lop);
                hocSinh.setTuoi_hs(tuoi);
                hocSinh.setGioitinh_hs(gioitinh);
                long kq = hocSinhDAO.addRow(hocSinh);

                if(kq>0){
                    listHocSinhs.clear();
                    listHocSinhs.addAll(hocSinhDAO.getAll());
                    notifyDataSetChanged();

                    Toast.makeText(context, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_ten.setText("");
                edt_lop.setText("");
                edt_tuoi.setText("");
                edt_gioitinh.setText("");
            }
        });
        dialog.show();
    }

    public void showDialogUpdate(Context context,int vitri,HocSinh hocSinh){
        final Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua);

        TextView tv_id = dialog.findViewById(R.id.tvIDSUA);
        EditText edt_ten = dialog.findViewById(R.id.edtTenSUA);
        EditText edt_lop = dialog.findViewById(R.id.edtLopSUA);
        EditText edt_tuoi = dialog.findViewById(R.id.edtTuoiSUA);
        EditText edt_gioitinh = dialog.findViewById(R.id.edtGioiTinhSUA);

        Button btnClear = dialog.findViewById(R.id.btnClearSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA);

        tv_id.setText(hocSinh.getId_hs()+"");
        edt_ten.setText(hocSinh.getTen_hs());
        edt_lop.setText(hocSinh.getLop_hs());
        edt_tuoi.setText(hocSinh.getTuoi_hs()+"");
        edt_gioitinh.setText(hocSinh.getGioitinh_hs());


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_ten.setText("");
                edt_lop.setText("");
                edt_tuoi.setText("");
                edt_gioitinh.setText("");
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();
                String lop = edt_lop.getText().toString();
                int tuoi = Integer.parseInt(edt_tuoi.getText().toString());
                String gioitinh = edt_gioitinh.getText().toString();

                hocSinh.setTen_hs(ten);
                hocSinh.setLop_hs(lop);
                hocSinh.setTuoi_hs(tuoi);
                hocSinh.setGioitinh_hs(gioitinh);

                int kq = hocSinhDAO.updateRow(hocSinh);
                if(kq>0){
                    listHocSinhs.set(vitri,hocSinh);
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
