package com.example.sondo.app_order_food.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sondo.app_order_food.DTO.BanAnDTO;
import com.example.sondo.app_order_food.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.Open();
    }
    public boolean ThemBanAn(String tenBanAn){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,tenBanAn);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,0);
        long kiemTra = database.insert(CreateDatabase.TB_BANAN,null,contentValues);
        if(kiemTra !=0)
            return true;
        else
            return false;
    }

    public List<BanAnDTO> layDanhSachBanAn(){
        List<BanAnDTO> dsBanAn = new ArrayList<BanAnDTO>();
        String query = "SELECT * FROM "+ CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            BanAnDTO banAn = new BanAnDTO();
            banAn.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAn.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));
            int tinhTrang = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG));
            banAn.setTinhTrang( ( tinhTrang == 1 )? true : false);
            dsBanAn.add(banAn);

            cursor.moveToNext();
        }
        return  dsBanAn;
    }

    public Boolean getTinhTrangBanAn(int maBan){
        String query = "SELECT " + CreateDatabase.TB_BANAN_TINHTRANG + " FROM "+ CreateDatabase.TB_BANAN +
                " WHERE " + CreateDatabase.TB_BANAN_MABAN + " = '" + maBan +"'";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        int tinhTrang = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANG));
        return ((tinhTrang==1)? true : false);
    }

    public Boolean CapNhatTinhTrangBanAn(int maBan,int tinhTrang){

      ContentValues contentValues = new ContentValues();
      contentValues.put(CreateDatabase.TB_BANAN_TINHTRANG,tinhTrang);

      int kq = database.update(CreateDatabase.TB_BANAN,contentValues,CreateDatabase.TB_BANAN_MABAN +" ='" + maBan +"'",null);
      if (kq !=0){
          return true;
      }
      return false;
    }

    public boolean SuaBanAn(BanAnDTO banAnDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,banAnDTO.getTenBan());
        int kq = database.update(CreateDatabase.TB_BANAN,contentValues,CreateDatabase.TB_BANAN_MABAN + " = ?",
                new String[]{String.valueOf(banAnDTO.getMaBan())});
        if(kq != 0)
            return true;
        return false;
    }

    public boolean XoaBanAn(int maBan){
        int kq = database.delete(CreateDatabase.TB_BANAN,CreateDatabase.TB_BANAN_MABAN + " = ?",
                new String[]{String.valueOf(maBan)});
        if (kq != 0)
            return true;
        return false;

    }


}
