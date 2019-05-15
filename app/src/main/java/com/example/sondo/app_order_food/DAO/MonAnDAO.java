package com.example.sondo.app_order_food.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sondo.app_order_food.DTO.MonAnDTO;
import com.example.sondo.app_order_food.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    SQLiteDatabase database;
    public MonAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.Open();
    }

    public boolean ThemMonAn(MonAnDTO monAnDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN,monAnDTO.getTenMon());
        contentValues.put(CreateDatabase.TB_MONAN_GIATIEN,monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANH,monAnDTO.getHinhAnh());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAI,monAnDTO.getMaLoai());

        long kt = database.insert(CreateDatabase.TB_MONAN,null,contentValues);
        if(kt != 0 )
            return true;
        return false;
    }

    public List<MonAnDTO> DanhSachMonAnTheoLoai(int maLoai){

        List<MonAnDTO> dsMonAn = new ArrayList<>();
        String query = "SELECT * FROM "+CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAI
                + " = '"+maLoai+"' ORDER BY "+CreateDatabase.TB_MONAN_MAMONAN + " DESC";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            MonAnDTO monAnDTO = new MonAnDTO();
            monAnDTO.setMaMon(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMONAN)));
            monAnDTO.setTenMon(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANH)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIATIEN)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAI)));

            dsMonAn.add(monAnDTO);
            cursor.moveToNext();
        }

        return dsMonAn;
    }

}
