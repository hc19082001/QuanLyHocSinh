package com.example.quanlyhocsinh.ClassRelated;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;

import java.util.ArrayList;

public class LopAdapter extends BaseAdapter {

    ArrayList<Lop> lopArrayList;
    LopDAO lopDAO;

    public LopAdapter(ArrayList<Lop> lopArrayList, LopDAO lopDAO) {
        this.lopArrayList = lopArrayList;
        this.lopDAO = lopDAO;
    }

    @Override
    public int getCount() {
        return lopArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return lopArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lopArrayList.get(position).getMa_lop();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;

        if(convertView==null){
            itemView = View.inflate(parent.getContext(), R.layout.item_listview_lop,null);
        }else
            itemView = convertView;

        Lop lop = lopArrayList.get(position);

        TextView tv_id = itemView.findViewById(R.id.tv_malop);
        TextView tv_ten = itemView.findViewById(R.id.tv_ten_lop);
        TextView tv_lop = itemView.findViewById(R.id.tv_sl);

        ImageButton img_btn_Sua = itemView.findViewById(R.id.imgbtnSua_lop);
        ImageButton img_btn_Xoa = itemView.findViewById(R.id.imgbtnXoa_lop);

        img_btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(parent.getContext(),position ,lop);
            }
        });

        img_btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("XÓA THÔNG TIN");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn có muốn xóa lớp : "+ lop.getTen_lop());
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = lopDAO.deleteRow(lop);
                        if(kq>0){
                            lopArrayList.remove(position);
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

        tv_id.setText(lop.getMa_lop()+"");
        tv_ten.setText(lop.getTen_lop());
        tv_lop.setText(lop.getSo_luong()+"");

        return itemView;
    }

    public void showDialogUpdate(Context context, int vitri, Lop lop){

        final Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_sua_lop);

        TextView tv_id = dialog.findViewById(R.id.tvIDSUA_lop);
        EditText edt_ten = dialog.findViewById(R.id.edtTenSUA_lop);
        EditText edt_sl = dialog.findViewById(R.id.edtSoLuongSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA_lop);

        tv_id.setText(lop.getMa_lop()+"");
        edt_ten.setText(lop.getTen_lop());
        edt_sl.setText(lop.getSo_luong()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();
                int slg = Integer.parseInt(edt_sl.getText().toString()) ;

                lop.setTen_lop(ten);
                lop.setSo_luong(slg);

                int kq = lopDAO.updateRow(lop);
                if(kq>0){
                    lopArrayList.set(vitri,lop);
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
