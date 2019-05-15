package com.example.sondo.app_order_food.DTO;

import java.io.Serializable;

public class BanAnDTO implements Serializable {
    private int maBan;
    private String tenBan;

    private boolean isClick;
    private boolean tinhTrang;

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }


    public BanAnDTO() {
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public BanAnDTO(int maBan, String tenBan) {
        this.maBan = maBan;
        this.tenBan = tenBan;
    }
}
