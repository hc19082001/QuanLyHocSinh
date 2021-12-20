package com.example.quanlyhocsinh.GradeRelated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.R;
import com.example.quanlyhocsinh.StudentRelated.HocSinh;
import com.example.quanlyhocsinh.StudentRelated.HocSinhDAO;
import com.example.quanlyhocsinh.SubjectRelated.MonHoc;

import java.util.ArrayList;

public class DiemAdapter extends BaseAdapter {

    HocSinhDAO hocSinhDAO;
    ArrayList<HocSinh> hocSinhArrayList;

    public DiemAdapter(HocSinhDAO hocSinhDAO, ArrayList<HocSinh> hocSinhArrayList) {
        this.hocSinhDAO = hocSinhDAO;
        this.hocSinhArrayList = hocSinhArrayList;
    }


    @Override
    public int getCount() {
        return hocSinhArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return hocSinhArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return hocSinhArrayList.get(position).getId_hs();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;

        if(convertView==null){
            itemView = View.inflate(parent.getContext(), R.layout.item_listview_diemhs,null);
        }else
            itemView = convertView;

        HocSinh hocSinh = hocSinhArrayList.get(position);

        TextView tv_id = itemView.findViewById(R.id.tv_id_diem);
        TextView tv_ten = itemView.findViewById(R.id.tv_ten_diem);
        TextView tv_lop = itemView.findViewById(R.id.tv_lop_diem);
        ImageView imageViewdiem = itemView.findViewById(R.id.imv_gt_diem);

        tv_id.setText(hocSinh.getId_hs()+"");
        tv_ten.setText(hocSinh.getTen_hs());
        String lop = hocSinhDAO.getTenLop(hocSinh.getLop_hs());
        tv_lop.setText(lop);

        if (hocSinh.isGioitinh_hs()) {
            imageViewdiem.setImageResource(R.drawable.ic_baseline_boy_24);
        } else {
            imageViewdiem.setImageResource(R.drawable.ic_baseline_local_florist_24);
        }

        return itemView;
    }
}
