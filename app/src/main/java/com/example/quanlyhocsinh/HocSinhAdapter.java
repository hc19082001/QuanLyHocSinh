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
        ImageView img_GT = itemView.findViewById(R.id.imv_gt);
        ImageButton img_btn_Xem = itemView.findViewById(R.id.imgbtnThongTin);
        ImageButton img_btn_Sua = itemView.findViewById(R.id.imgbtnSua);
        ImageButton img_btn_Xoa = itemView.findViewById(R.id.imgbtnXoa);


        img_btn_Xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("Thông tin học sinh");
                String gt = "Nữ";
                if (hocSinh.isGioitinh_hs()) {
                    gt = "Nam";
                }
                builder.setIcon(android.R.drawable.ic_input_add);
                builder.setMessage("ID : "+hocSinh.getId_hs()+
                        "\nTên : "+hocSinh.getTen_hs()+
                        "\nLớp : "+hocSinh.getLop_hs()+
                        "\nNăm sinh : "+hocSinh.getNs_hs()+
                        "\ngiới tính : "+ gt +
                        "\nĐịa chỉ : "+hocSinh.getDiachi_hs());
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
        img_btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(viewGroup.getContext(),position ,hocSinh);
            }
        });
        img_btn_Xoa.setOnClickListener(new View.OnClickListener() {
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
        if (hocSinh.isGioitinh_hs()) {
            img_GT.setImageResource(R.drawable.ic_baseline_boy_24);
        } else {
            img_GT.setImageResource(R.drawable.ic_baseline_local_florist_24);
        }

        return itemView;
    }


    public void showDialogUpdate(Context context,int vitri,HocSinh hocSinh){

        final Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua);

        TextView tv_id = dialog.findViewById(R.id.tvIDSUA);
        EditText edt_ten = dialog.findViewById(R.id.edtTenSUA);
        EditText edt_lop = dialog.findViewById(R.id.edtLopSUA);
        EditText edt_NS = dialog.findViewById(R.id.edtNSSUA);
        EditText edt_diachi = dialog.findViewById(R.id.edtDiaChiSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA);
        RadioButton radioButtonNam = dialog.findViewById(R.id.rbtn_Nam_Update);
        RadioButton radioButtonNu = dialog.findViewById(R.id.rbtn_Nu_Update);
        RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup_Update);

        tv_id.setText(hocSinh.getId_hs()+"");
        edt_ten.setText(hocSinh.getTen_hs());
        edt_lop.setText(hocSinh.getLop_hs());
        edt_NS.setText(hocSinh.getNs_hs()+"");
        if (hocSinh.isGioitinh_hs()) {
            radioButtonNam.setChecked(true);
        } else {
            radioButtonNu.setChecked(true);
        }
        edt_diachi.setText(hocSinh.getDiachi_hs());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();
                String lop = edt_lop.getText().toString();
                int ns = Integer.parseInt(edt_NS.getText().toString());
                boolean gt = false;
                if (radioGroup.getCheckedRadioButtonId() == radioButtonNam.getId()) {
                    gt = true;
                }
                String diachi = edt_diachi.getText().toString();

                hocSinh.setTen_hs(ten);
                hocSinh.setLop_hs(lop);
                hocSinh.setNs_hs(ns);
                hocSinh.setGioitinh_hs(gt);
                hocSinh.setDiachi_hs(diachi);
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
