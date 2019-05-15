package com.example.sondo.app_order_food.DTO;

public class GoiMonDTO {
    private int maGoiMon;
    private int maNhanVien;
    private String ngayGoi;
    private boolean tinhTrang;
    private  int maBan;

    public GoiMonDTO() {
    }

    public GoiMonDTO(int maNhanVien, String ngayGoi, boolean tinhTrang, int maBan) {
        this.maNhanVien = maNhanVien;
        this.ngayGoi = ngayGoi;
        this.tinhTrang = tinhTrang;
        this.maBan = maBan;
    }

    public GoiMonDTO(int maGoiMon, int maNhanVien, String ngayGoi, boolean tinhTrang, int maBan) {
        this.maGoiMon = maGoiMon;
        this.maNhanVien = maNhanVien;
        this.ngayGoi = ngayGoi;
        this.tinhTrang = tinhTrang;
        this.maBan = maBan;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayGoi() {
        return ngayGoi;
    }

    public void setNgayGoi(String ngayGoi) {
        this.ngayGoi = ngayGoi;
    }

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
}
