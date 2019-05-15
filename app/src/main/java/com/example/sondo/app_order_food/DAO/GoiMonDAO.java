package com.example.sondo.app_order_food.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sondo.app_order_food.DTO.ChiTietGoiMonDTO;
import com.example.sondo.app_order_food.DTO.GoiMonDTO;
import com.example.sondo.app_order_food.DTO.ThanhToanDTO;
import com.example.sondo.app_order_food.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class GoiMonDAO {
    SQLiteDatabase database;

    public GoiMonDAO (Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.Open();
    }

    public Boolean GoiMon(GoiMonDTO goiMonDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_MANHANVIEN,goiMonDTO.getMaNhanVien());
        contentValues.put(CreateDatabase.TB_GOIMON_NGAYGOI,goiMonDTO.getNgayGoi());
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG,(goiMonDTO.getTinhTrang() == true) ? 1 : 0);
        contentValues.put(CreateDatabase.TB_GOIMON_MABAN,goiMonDTO.getMaBan());

        long kq = database.insert(CreateDatabase.TB_GOIMON,null,contentValues);
        if(kq != 0)
            return true;
        return false;
    }

    public int getMaGoiMonTheoMaBan(int maBan,int tinhTrang){

        int maGoiMon = 0;
        String query = "SELECT * FROM " + CreateDatabase.TB_GOIMON +" WHERE " + CreateDatabase.TB_GOIMON_MABAN +
                " = '" + maBan + "' AND " + CreateDatabase.TB_GOIMON_TINHTRANG + " = '" +tinhTrang +"'" ;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maGoiMon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));
            cursor.moveToNext();
        }
        return maGoiMon;
    }

    public boolean getTinhTrangGoiMon(int maBan){
        int tinhTrang = 0;
        String query = "SELECT * FROM " + CreateDatabase.TB_GOIMON +" WHERE " + CreateDatabase.TB_GOIMON_MABAN +
                " = '" + maBan + "'" ;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tinhTrang = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_TINHTRANG));
            cursor.moveToNext();
        }
        return (tinhTrang ==1) ? true: false;
    }


    public int KiemTraMonAnTonTai(int magoimon,int mamonan)
    {
        int soLuongHienTai = 0;
        String query = "SELECT * FROM " +CreateDatabase.TB_CHITIETGOIMON + " WHERE " +
              CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = '" + magoimon + "' AND " +
              CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " ='" + mamonan +"'" ;

        Cursor cursor = database.rawQuery(query,null);
        if(cursor.getCount() != 0) //Mon An da ton tai
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                soLuongHienTai = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG));
                cursor.moveToNext();
            }
        }
        return soLuongHienTai;

    }

    public boolean ThemVaoChiTietGoiMon(ChiTietGoiMonDTO chiTietGoiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAGOIMON,chiTietGoiMonDTO.getMaGoiMon());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAMONAN,chiTietGoiMonDTO.getMaMonAn());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONG,chiTietGoiMonDTO.getSoLuong());

        long kq = database.insert(CreateDatabase.TB_CHITIETGOIMON,null,contentValues);
        if (kq != 0)
            return true;
        return false;
    }

    public boolean CapNhatSoLuongMonAn(ChiTietGoiMonDTO chiTietGoiMonDTO){
        String query = "UPDATE " + CreateDatabase.TB_CHITIETGOIMON + " SET " + CreateDatabase.TB_CHITIETGOIMON_SOLUONG
                + " = '" + chiTietGoiMonDTO.getSoLuong() + "' WHERE " +
                CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " = '" + chiTietGoiMonDTO.getMaGoiMon()+ "' AND " +
                CreateDatabase.TB_CHITIETGOIMON_MAMONAN + " ='" + chiTietGoiMonDTO.getMaMonAn() +"'" ;

        Cursor cursor = database.rawQuery(query,null);
        long kq = cursor.getCount();
        if(kq != 0)
            return true;
        return false;
    }

    public List<ThanhToanDTO> HienThiDanhSachMonAnThanhToan(int maGoiMon){
        List<ThanhToanDTO> dsThanhToan = new ArrayList<ThanhToanDTO>();

        String query = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + "," + CreateDatabase.TB_MONAN +
                " WHERE " + CreateDatabase.TB_CHITIETGOIMON + "." + CreateDatabase.TB_CHITIETGOIMON_MAMONAN +
                " = " + CreateDatabase.TB_MONAN + "." +CreateDatabase.TB_MONAN_MAMONAN + " AND " +
                CreateDatabase.TB_CHITIETGOIMON_MAGOIMON + " ='" + maGoiMon +"'";

        Log.d("query",query);

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String tenMonAn = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN));
            int soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONG)));
            long giaTien = Long.parseLong(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));

            ThanhToanDTO thanhToanDTO = new ThanhToanDTO(tenMonAn,soLuong,giaTien);
            Log.d("data","gia tien "+thanhToanDTO.getGiaTien());
            dsThanhToan.add(thanhToanDTO);
            cursor.moveToNext();
        }

        return dsThanhToan;
    }

    public boolean CapNhatTinhTrangGoiMon(int maBan,int tinhTrang){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANG,tinhTrang);

        int kq = database.update(CreateDatabase.TB_GOIMON,contentValues,
                CreateDatabase.TB_GOIMON_MABAN + " = ?",new  String[]{String.valueOf(maBan)});
        if(kq !=0)
            return true;
        return false;
    }



}
