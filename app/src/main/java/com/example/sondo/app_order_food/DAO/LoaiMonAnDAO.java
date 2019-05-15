package com.example.sondo.app_order_food.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnDAO {
    SQLiteDatabase database;
    public LoaiMonAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.Open();
    }

    public boolean ThemLoaiMonAn(LoaiMonAnDTO loaiMonAnDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAI,loaiMonAnDTO.getTenLoai());
        long kt = database.insert(CreateDatabase.TB_LOAIMONAN,null,contentValues);
        if(kt != 0 )
            return true;
        return false;
    }

    public List<LoaiMonAnDTO> HienThiDanhSachLoaiMonAn(){
        List<LoaiMonAnDTO> dsLoaiMonAn = new ArrayList<LoaiMonAnDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_MALOAI)));
            loaiMonAnDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_TENLOAI)));

            dsLoaiMonAn.add(loaiMonAnDTO);
            cursor.moveToNext();
        }
        return dsLoaiMonAn;
    }

    public String getHinhAnhMonAnBangMaLoai(int maloai){
        String hinhAnh ="";
        String query = "SELECT * FROM "+CreateDatabase.TB_MONAN + " WHERE " +
                CreateDatabase.TB_MONAN_MALOAI +" = '"+ maloai +"' AND "+CreateDatabase.TB_MONAN_HINHANH + " != '' ORDER BY " + CreateDatabase.TB_MONAN_MALOAI +
                "," + CreateDatabase.TB_MONAN_MAMONAN + " DESC LIMIT 1";
        Log.d("query",query);
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            hinhAnh = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH));
            cursor.moveToNext();
        }

        return  hinhAnh;
    }

    public boolean SuaThucDon(LoaiMonAnDTO loaiMonAnDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAI,loaiMonAnDTO.getTenLoai());
        int kq = database.update(CreateDatabase.TB_LOAIMONAN,contentValues,CreateDatabase.TB_LOAIMONAN_MALOAI + " = ?",
                new String[]{String.valueOf(loaiMonAnDTO.getMaLoai())});
        if (kq != 0)
            return true;
        return false;
    }


    public boolean XoaThucDon(int maLoai){
        int kq = database.delete(CreateDatabase.TB_LOAIMONAN,CreateDatabase.TB_LOAIMONAN_MALOAI + " = ?",
                new String[]{String.valueOf(maLoai)});
        if (kq != 0)
            return true;
        return false;
    }


}
