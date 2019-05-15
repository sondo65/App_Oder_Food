package com.example.sondo.app_order_food.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sondo.app_order_food.DTO.NhanVienDTO;
import com.example.sondo.app_order_food.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    SQLiteDatabase database;
    public NhanVienDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.Open();
    }

    public List<String> KiemTraTrungMa(String tenDangNhap){
        List<String> listTenDangNhap = new ArrayList<String>();
        String sCauTruyVan = "SELECT "+ CreateDatabase.TB_NHANVIEN_TENDANGNHAP+" FROM "+
                CreateDatabase.TB_NHANVIEN;

        Cursor cursor = database.rawQuery(sCauTruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String sTenDangNhap = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_TENDANGNHAP));
            listTenDangNhap.add(sTenDangNhap);
            cursor.moveToNext();
        }
        cursor.close();
        return listTenDangNhap;
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_NHANVIEN_TENDANGNHAP,nhanVienDTO.getTenDangNhap());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAU,nhanVienDTO.getMatKhau());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINH,nhanVienDTO.getGioiTinh());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINH,nhanVienDTO.getNgaySinh());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMND,nhanVienDTO.getCMND());

        long kiemTra = database.insert(CreateDatabase.TB_NHANVIEN,null,contentValues);
        return kiemTra;
    }

    public int KiemTraDangNhap(NhanVienDTO nhanVienDTO){
        String tenDangNhap = nhanVienDTO.getTenDangNhap().toString();
        String matKhau = nhanVienDTO.getMatKhau().toString();

        String sCauTruyVan = "SELECT * FROM "+
                CreateDatabase.TB_NHANVIEN +" WHERE "+CreateDatabase.TB_NHANVIEN_TENDANGNHAP + "= '"+tenDangNhap+
                "' AND "+CreateDatabase.TB_NHANVIEN_MATKHAU+" = '"+matKhau+"'";
        Cursor cursor = database.rawQuery(sCauTruyVan,null);
        int maNhanVien = 0;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maNhanVien = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANHANVIEN));
            cursor.moveToNext();
        }
        return maNhanVien;
    }

}
