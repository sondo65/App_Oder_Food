package com.example.sondo.app_order_food.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_NHANVIEN = "NhanVien";
    public static String TB_NHANVIEN_MANHANVIEN = "MaNhanVien";
    public static String TB_NHANVIEN_TENDANGNHAP = "TenDangNhap";
    public static String TB_NHANVIEN_MATKHAU = "MatKhau";
    public static String TB_NHANVIEN_GIOITINH = "GioiTinh";
    public static String TB_NHANVIEN_NGAYSINH = "NgaySinh";
    public static String TB_NHANVIEN_CMND = "CMND";

    public static String TB_MONAN = "MonAn";
    public static String TB_MONAN_MAMONAN = "MaMonAn";
    public static String TB_MONAN_TENMONAN = "TenMonAn";
    public static String TB_MONAN_GIATIEN = "GiaTien";
    public static String TB_MONAN_MALOAI = "MaLoai";
    public static String TB_MONAN_HINHANH = "HinhAnh";

    public static String TB_LOAIMONAN = "LoaiMonAn";
    public static String TB_LOAIMONAN_MALOAI = "MaLoai";
    public static String TB_LOAIMONAN_TENLOAI = "TenLoai";

    public static String TB_GOIMON = "GoiMon";
    public static String TB_GOIMON_MAGOIMON = "MaGoiMon";
    public static String TB_GOIMON_MANHANVIEN = "MaNhanVien";
    public static String TB_GOIMON_NGAYGOI = "NgayGoi";
    public static String TB_GOIMON_TINHTRANG = "TinhTrang";
    public static String TB_GOIMON_MABAN = "MaBan";

    public static String TB_CHITIETGOIMON = "ChiTietGoiMon";
    public static String TB_CHITIETGOIMON_MAGOIMON = "MaGoiMon";
    public static String TB_CHITIETGOIMON_MAMONAN = "MaMonAn";
    public static String TB_CHITIETGOIMON_SOLUONG = "SoLuong";

    public static String TB_BANAN = "BanAn";
    public static String TB_BANAN_MABAN = "MaBan";
    public static String TB_BANAN_TENBAN = "TenBan";
    public static String TB_BANAN_TINHTRANG = "TinhTrang";



    public CreateDatabase(Context context) {
        super(context, "Database_OderFood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbNHANVIEN = "CREATE TABLE "+TB_NHANVIEN + "(" + TB_NHANVIEN_MANHANVIEN + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                TB_NHANVIEN_TENDANGNHAP +" TEXT ," + TB_NHANVIEN_MATKHAU +" TEXT ," + TB_NHANVIEN_GIOITINH +" INTEGER,"+
                TB_NHANVIEN_NGAYSINH+" TEXT," + TB_NHANVIEN_CMND + " TEXT )";

        String tbMONAN = "CREATE TABLE " + TB_MONAN + "("+TB_MONAN_MAMONAN +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                TB_MONAN_TENMONAN +" TEXT,"+TB_MONAN_GIATIEN +" TEXT,"+TB_MONAN_MALOAI +" INTEGER ," + TB_MONAN_HINHANH + " TEXT )";

        String tbLOAIMONAN = "CREATE TABLE "+TB_LOAIMONAN+" ( "+TB_LOAIMONAN_MALOAI +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                TB_LOAIMONAN_TENLOAI +" TEXT )";

        String tbGOIMON = "CREATE TABLE "+ TB_GOIMON +"( "+TB_GOIMON_MAGOIMON +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                TB_GOIMON_MANHANVIEN +" INTEGER ,"+ TB_GOIMON_NGAYGOI +" TEXT ,"+TB_GOIMON_TINHTRANG +" INTEGER ,"+ TB_GOIMON_MABAN +" INTEGER )";

        String tbChiTietGoiMon = "CREATE TABLE "+ TB_CHITIETGOIMON +" ( "+TB_CHITIETGOIMON_MAGOIMON +" INTEGER ," +
                TB_CHITIETGOIMON_MAMONAN+ " INTEGER ," + TB_CHITIETGOIMON_SOLUONG +" INTEGER ,"+
                " PRIMARY KEY ( " + TB_CHITIETGOIMON_MAGOIMON +","+TB_CHITIETGOIMON_MAMONAN +"))";

        String tbBANAN = "CREATE TABLE "+TB_BANAN +" ( "+TB_BANAN_MABAN +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                TB_BANAN_TENBAN + " TEXT ,"+ TB_BANAN_TINHTRANG +" INTEGER )";

        db.execSQL(tbNHANVIEN);
        db.execSQL(tbMONAN);
        db.execSQL(tbLOAIMONAN);
        db.execSQL(tbGOIMON);
        db.execSQL(tbChiTietGoiMon);
        db.execSQL(tbBANAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sDrop_TB_NHANVIEN = "DROP TABLE IF EXISTS " + TB_NHANVIEN;
        String sDrop_TB_MONAN = "DROP TABLE IF EXISTS " + TB_MONAN;
        String sDrop_TB_LOAIMONAN = "DROP TABLE IF EXISTS " + TB_LOAIMONAN;
        String sDrop_TB_GOIMON = "DROP TABLE IF EXISTS " + TB_GOIMON;
        String sDrop_TB_CHITIETGOIMON = "DROP TABLE IF EXISTS " + TB_CHITIETGOIMON;
        String sDrop_TB_BANAN = "DROP TABLE IF EXISTS " + TB_BANAN;

        db.execSQL(sDrop_TB_NHANVIEN);
        db.execSQL(sDrop_TB_MONAN);
        db.execSQL(sDrop_TB_LOAIMONAN);
        db.execSQL(sDrop_TB_GOIMON);
        db.execSQL(sDrop_TB_CHITIETGOIMON);
        db.execSQL(sDrop_TB_BANAN);

        onCreate(db);
    }

    public SQLiteDatabase Open(){
        return this.getWritableDatabase();
    }


}

