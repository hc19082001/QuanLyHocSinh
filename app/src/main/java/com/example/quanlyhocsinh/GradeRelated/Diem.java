package com.example.quanlyhocsinh.GradeRelated;

public class Diem {

    private int id_hs;  // Foreign Key
    private int ma_mh;  // Foreign Key
    private double diemQT;
    private double diemGK;
    private double diemCK;

    public static String DIEM_DB_NAME = "tb_diem";
    public static String COL_IDHS = "id_hs";
    public static String COL_MAMH = "ma_mh";
    public static String COL_DQT = "diemQT";
    public static String COL_DGK = "diemGK";
    public static String COL_DCK = "diemCK";

    public Diem(int id_hs, int ma_mh, double diemQT, double diemGK, double diemCK) {
        this.id_hs = id_hs;
        this.ma_mh = ma_mh;
        this.diemQT = diemQT;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
    }

    public Diem() {
    }

    public int getId_hs() {
        return id_hs;
    }

    public void setId_hs(int id_hs) {
        this.id_hs = id_hs;
    }

    public int getMa_mh() {
        return ma_mh;
    }

    public void setMa_mh(int ma_mh) {
        this.ma_mh = ma_mh;
    }

    public double getDiemQT() {
        return diemQT;
    }

    public void setDiemQT(double diemQT) {
        this.diemQT = diemQT;
    }

    public double getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(double diemGK) {
        this.diemGK = diemGK;
    }

    public double getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(double diemCK) {
        this.diemCK = diemCK;
    }
}
