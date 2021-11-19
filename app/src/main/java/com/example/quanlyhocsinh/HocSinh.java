package com.example.quanlyhocsinh;

public class HocSinh {

    public int id_hs;
    public String ten_hs;
    public String lop_hs;
    public int tuoi_hs;
    public String gioitinh_hs;

    public static final String TB_NAME = "tb_hocsinh";
    public static final String COL_ID = "id_hs";
    public static final String COL_TEN = "ten_hs";
    public static final String COL_LOP = "lop_hs";
    public static final String COL_TUOI = "tuoi_hs";
    public static final String COL_GIOITINH = "gioitinh_hs";

    public int getId_hs() {
        return id_hs;
    }

    public void setId_hs(int id_hs) {
        this.id_hs = id_hs;
    }

    public String getTen_hs() {
        return ten_hs;
    }

    public void setTen_hs(String ten_hs) {
        this.ten_hs = ten_hs;
    }

    public String getLop_hs() {
        return lop_hs;
    }

    public void setLop_hs(String lop_hs) {
        this.lop_hs = lop_hs;
    }

    public int getTuoi_hs() {
        return tuoi_hs;
    }

    public void setTuoi_hs(int tuoi_hs) {
        this.tuoi_hs = tuoi_hs;
    }

    public String getGioitinh_hs() {
        return gioitinh_hs;
    }

    public void setGioitinh_hs(String gioitinh_hs) {
        this.gioitinh_hs = gioitinh_hs;
    }
}
