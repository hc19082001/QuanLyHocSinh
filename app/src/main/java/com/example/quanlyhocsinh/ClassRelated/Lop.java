package com.example.quanlyhocsinh.ClassRelated;

public class Lop {
    private int ma_lop;
    private String ten_lop;
    private int so_luong;

    public static String DB_LOP_NAME = "Tb_Lop";
    public static String COL_MALOP = "ma_lop";
    public static String COL_TENLOP = "ten_lop";
    public static String COL_SOLUONG = "so_luong";

    public Lop(int ma_lop, String ten_lop, int so_luong) {
        this.ma_lop = ma_lop;
        this.ten_lop = ten_lop;
        this.so_luong = so_luong;
    }

    public Lop(String ten_lop, int so_luong) {
        this.ten_lop = ten_lop;
        this.so_luong = so_luong;
    }

    public Lop() {
    }

    public int getMa_lop() {
        return ma_lop;
    }

    public void setMa_lop(int ma_lop) {
        this.ma_lop = ma_lop;
    }

    public String getTen_lop() {
        return ten_lop;
    }

    public void setTen_lop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    @Override
    public String toString() {
        return this.ten_lop;
    }

}
