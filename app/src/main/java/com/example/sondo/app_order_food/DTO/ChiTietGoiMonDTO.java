package com.example.sondo.app_order_food.DTO;

public class ChiTietGoiMonDTO {
    private int maGoiMon;
    private int maMonAn;
    private int soLuong;

    public ChiTietGoiMonDTO(int maGoiMon, int maMonAn, int soLuong) {
        this.maGoiMon = maGoiMon;
        this.maMonAn = maMonAn;
        this.soLuong = soLuong;
    }

    public ChiTietGoiMonDTO() {
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
