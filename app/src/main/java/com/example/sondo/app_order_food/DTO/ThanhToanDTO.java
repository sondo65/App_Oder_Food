package com.example.sondo.app_order_food.DTO;

public class ThanhToanDTO {
    private String tenMonAn;
    private  int soLuong;
    private  long giaTien;

    public ThanhToanDTO(String tenMonAn, int soLuong, long giaTien) {
        this.tenMonAn = tenMonAn;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public ThanhToanDTO() {
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }
}
