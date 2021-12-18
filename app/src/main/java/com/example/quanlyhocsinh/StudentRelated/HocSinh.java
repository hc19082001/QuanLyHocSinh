package com.example.quanlyhocsinh.StudentRelated;

public class HocSinh {

    private int id_hs;
    private String ten_hs;
    private int lop_hs;        // Foreign Key
    private int ns_hs;
    private boolean gioitinh_hs;
    private String diachi_hs;


    public static final String TB_NAME = "tb_hocsinh";
    public static final String COL_ID = "id_hs";
    public static final String COL_TEN = "ten_hs";
    public static final String COL_LOP = "lop_hs";
    public static final String COL_NAMSINH = "ns_hs";
    public static final String COL_GIOITINH = "gioitinh_hs";
    public static final String COL_DIACHI = "diachi_hs";

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

    public int getLop_hs() {
        return lop_hs;
    }

    public void setLop_hs(int lop_hs) {
        this.lop_hs = lop_hs;
    }

    public int getNs_hs() {
        return ns_hs;
    }

    public void setNs_hs(int ns_hs) {
        this.ns_hs = ns_hs;
    }

    public boolean isGioitinh_hs() {
        return gioitinh_hs;
    }

    public void setGioitinh_hs(boolean gioitinh_hs) {
        this.gioitinh_hs = gioitinh_hs;
    }

    public String getDiachi_hs() {
        return diachi_hs;
    }

    public void setDiachi_hs(String diachi_hs) {
        this.diachi_hs = diachi_hs;
    }

}
