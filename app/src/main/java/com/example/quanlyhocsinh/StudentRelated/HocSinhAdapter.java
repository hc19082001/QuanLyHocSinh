package com.example.quanlyhocsinh.StudentRelated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.security.ConfirmationNotAvailableException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlyhocsinh.ClassRelated.Lop;
import com.example.quanlyhocsinh.ClassRelated.LopDAO;
import com.example.quanlyhocsinh.R;

import java.util.ArrayList;

public class HocSinhAdapter extends BaseAdapter {

    ArrayList<HocSinh> listHocSinhs;
    HocSinhDAO hocSinhDAO;
    LopDAO lopDAO;
    ArrayList<Lop> lopArrayList ;

    public HocSinhAdapter(ArrayList<HocSinh> listHocSinhs, HocSinhDAO hocSinhDAO, LopDAO lopDAO) {
        this.lopDAO = lopDAO;
        this.listHocSinhs = listHocSinhs;
        this.hocSinhDAO = hocSinhDAO;
        this.lopDAO = lopDAO;
        this.lopArrayList = lopDAO.getAll();
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
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_listview_hocsinh,null);
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
                builder.setIcon(R.drawable.ic_baseline_remove_red_eye_24);
                int vitript = layViTriPTLop(hocSinh.getLop_hs(), lopArrayList);
                builder.setMessage("ID : "+hocSinh.getId_hs()+
                        "\nTên : "+hocSinh.getTen_hs()+
                        "\nLớp : "+ lopArrayList.get(vitript).getTen_lop() +
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
                        hocSinhDAO.deleteDataOnWeb(hocSinh.getId_hs());
                        Toast.makeText(viewGroup.getContext(), "XÓA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        listHocSinhs.remove(hocSinh);
                        notifyDataSetChanged();
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

        tv_id.setText(hocSinh.getId_hs()+ "");
        tv_ten.setText(hocSinh.getTen_hs());
        int vitri = layViTriPTLop(hocSinh.getLop_hs(), lopArrayList);
        tv_lop.setText(lopArrayList.get(vitri).getTen_lop());

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
        Spinner sp_lop = dialog.findViewById(R.id.spinner_sualop);
        EditText edt_NS = dialog.findViewById(R.id.edtNSSUA);
        EditText edt_diachi = dialog.findViewById(R.id.edtDiaChiSUA);
        Button btnUpdate = dialog.findViewById(R.id.btnCapNhatSUA);
        RadioButton radioButtonNam = dialog.findViewById(R.id.rbtn_Nam_Update);
        RadioButton radioButtonNu = dialog.findViewById(R.id.rbtn_Nu_Update);
        RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup_Update);

        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<>(
                context,
                R.layout.support_simple_spinner_dropdown_item,
                lopArrayList
        );
        sp_lop.setAdapter(lopArrayAdapter);

        tv_id.setText(hocSinh.getId_hs()+"");
        edt_ten.setText(hocSinh.getTen_hs());
        edt_NS.setText(hocSinh.getNs_hs()+"");
        if (hocSinh.isGioitinh_hs()) {
            radioButtonNam.setChecked(true);
        } else {
            radioButtonNu.setChecked(true);
        }
        edt_diachi.setText(hocSinh.getDiachi_hs());

        int position = layViTriPTLop(hocSinh.getLop_hs(), lopArrayList);
        sp_lop.setSelection(position);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edt_ten.getText().toString();

                Lop lop = (Lop) sp_lop.getSelectedItem();

                int malop = lop.getMa_lop();
                int ns = Integer.parseInt(edt_NS.getText().toString());
                boolean gt = false;
                if (radioGroup.getCheckedRadioButtonId() == radioButtonNam.getId()) {
                    gt = true;
                }
                String diachi = edt_diachi.getText().toString();

                int slhs = getSoLuongHS(malop);
                int slhs_ht = hocSinhDAO.getSLHSmotLop(malop);
                
                if (slhs_ht >= slhs && hocSinh.getLop_hs() != malop ) {
                        Toast.makeText(context, "SỐ LƯỢNG HS TRONG LỚP ĐÃ CHỌN ĐÃ ĐỦ, MỜI CHỌN LỚP KHÁC!", Toast.LENGTH_SHORT).show();
                } else  {
                    hocSinh.setTen_hs(ten);
                    hocSinh.setLop_hs(malop);
                    hocSinh.setNs_hs(ns);
                    hocSinh.setGioitinh_hs(gt);
                    hocSinh.setDiachi_hs(diachi);
                    hocSinhDAO.updateRow(hocSinh);
                    hocSinhDAO.updateDataToWeb(hocSinh);

                    listHocSinhs.set(vitri,hocSinh);
                    notifyDataSetChanged();
                    Toast.makeText(context, "SỬA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                }

            }
        });
        dialog.show();
    }

    private int getSoLuongHS(int malop) {
        for (Lop x :
                lopArrayList) {
            if (x.getMa_lop() == malop) {
                return x.getSo_luong();
            }
            }
        return -1;
    }


    private int layViTriPTLop(int malop, ArrayList<Lop> lops) {

        for (Lop x: lops) {
            if (x.getMa_lop() == malop) {
                return lops.indexOf(x);
            }
        }
        return -1;
    }


}
