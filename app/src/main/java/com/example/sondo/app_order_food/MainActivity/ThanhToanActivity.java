package com.example.sondo.app_order_food.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sondo.app_order_food.CustomAdapter.AdapterHienThiMonAnThanhToan;
import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DAO.GoiMonDAO;
import com.example.sondo.app_order_food.DTO.ThanhToanDTO;
import com.example.sondo.app_order_food.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {

    GoiMonDAO goiMonDAO;
    BanAnDAO banAnDAO;
    List<ThanhToanDTO> dsThanhToan;
    ListView lvThanhToan;
    AdapterHienThiMonAnThanhToan adapterHienThiMonAnThanhToan;
    Button btnThanhToan,btnThoat;
    TextView txtTongTien;
    int maBan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        lvThanhToan = findViewById(R.id.lvThanhToan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThoat = findViewById(R.id.btnThoatThanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        goiMonDAO = new GoiMonDAO(this);
        banAnDAO = new BanAnDAO(this);
        maBan = getIntent().getIntExtra("maban",0);
        if(maBan > 0 ){
            int maGoiMon = goiMonDAO.getMaGoiMonTheoMaBan(maBan,0);
            Log.d("data","maGoiMon"+maGoiMon);
            dsThanhToan = goiMonDAO.HienThiDanhSachMonAnThanhToan(maGoiMon);
            Log.d("data","dsThanhToan"+dsThanhToan.size());
            adapterHienThiMonAnThanhToan = new AdapterHienThiMonAnThanhToan(this,R.layout.layout_custom_hienthimonan_thanhtoan,dsThanhToan);
            lvThanhToan.setAdapter(adapterHienThiMonAnThanhToan);
            adapterHienThiMonAnThanhToan.notifyDataSetChanged();
        }

        long tongTien = 0;
        for(int i = 0;i<dsThanhToan.size();i++){
            tongTien += dsThanhToan.get(i).getGiaTien()* dsThanhToan.get(i).getSoLuong();
        }

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String strTongTien = currencyVN.format(tongTien);
        txtTongTien.setText(strTongTien);

        btnThanhToan.setOnClickListener(this);
        btnThoat.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThanhToan:
                boolean kqGM = goiMonDAO.CapNhatTinhTrangGoiMon(maBan,1);
                boolean kqBA = banAnDAO.CapNhatTinhTrangBanAn(maBan,0);
                if(kqBA && kqGM)
                    Toast.makeText(this,"Thanh toán thành công !",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Thanh toán thất bại !",Toast.LENGTH_SHORT).show();
                finish();
                ;break;
            case R.id.btnThoatThanhToan:
                finish();
                ;break;
        }
    }
}
