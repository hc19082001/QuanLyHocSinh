package com.example.quanlyhocsinh.SubjectRelated;

public class MonHoc {

    private int ma_mh;
    private String ten_mh;
    private int so_tinchi;

    public static String COL_MAMH = "ma_mh";
    public static String COL_TENMH = "ten_mh";
    public static String COL_SOTINCHI = "so_tinchi";
    public static String TB_MONHOC_NAME = "tb_Monhoc";

    public MonHoc() {
    }

    public MonHoc(int ma_mh, String ten_mh, int so_tinchi) {
        this.ma_mh = ma_mh;
        this.ten_mh = ten_mh;
        this.so_tinchi = so_tinchi;
    }

    public MonHoc(String ten_mh, int so_tinchi) {
        this.ten_mh = ten_mh;
        this.so_tinchi = so_tinchi;
    }

    public int getMa_mh() {
        return ma_mh;
    }

    public void setMa_mh(int ma_mh) {
        this.ma_mh = ma_mh;
    }

    public String getTen_mh() {
        return ten_mh;
    }

    public void setTen_mh(String ten_mh) {
        this.ten_mh = ten_mh;
    }

    public int getSo_tinchi() {
        return so_tinchi;
    }

    public void setSo_tinchi(int so_tinchi) {
        this.so_tinchi = so_tinchi;
    }

    @Override
    public String toString() {
        return this.ten_mh;
    }
}
