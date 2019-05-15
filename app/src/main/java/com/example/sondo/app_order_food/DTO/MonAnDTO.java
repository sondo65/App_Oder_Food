package com.example.sondo.app_order_food.DTO;

public class MonAnDTO {
    private int maMon;
    private String tenMon;
    private String giaTien;
    private String hinhAnh;
    private int maLoai;

    public MonAnDTO() {
    }

    public MonAnDTO(String tenMon, String giaTien, String hinhAnh, int maLoai) {
        this.tenMon = tenMon;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.maLoai = maLoai;
    }

    public MonAnDTO(int maMon, String tenMon, String giaTien, String hinhAnh, int maLoai) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.maLoai = maLoai;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
