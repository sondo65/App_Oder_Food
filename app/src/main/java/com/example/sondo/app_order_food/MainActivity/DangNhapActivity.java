package com.example.sondo.app_order_food.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.NhanVienDAO;
import com.example.sondo.app_order_food.DTO.NhanVienDTO;
import com.example.sondo.app_order_food.R;

import java.util.List;

public class DangNhapActivity extends Activity implements View.OnClickListener {
    EditText edtTenDN,edtMatKhau;
    Button btnDangKy,btnDongY;
    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);
        edtTenDN = findViewById(R.id.edtTenDN);
        edtMatKhau = findViewById(R.id.edtMatKhau);

        btnDangKy = findViewById(R.id.btnDangKy);
        btnDongY = findViewById(R.id.btnDongY);

        btnDangKy.setOnClickListener(this);
        btnDongY.setOnClickListener(this);

        HienThiButtonDongYHoacDangKy();

    }

    private void HienThiButtonDongYHoacDangKy(){
        String sTenDangNhap = edtTenDN.getText().toString();
        nhanVienDAO = new NhanVienDAO(this);
        List<String> lsNhanVien =  nhanVienDAO.KiemTraTrungMa(sTenDangNhap);
        if(lsNhanVien.isEmpty()){
            btnDongY.setVisibility(View.GONE);
            btnDangKy.setVisibility(View.VISIBLE);
        }else
        {
            btnDangKy.setVisibility(View.GONE);
            btnDongY.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButtonDongYHoacDangKy();
    }

    private void KiemTraDangNhap(){
        String sTenDangNhap = edtTenDN.getText().toString();
        String sMatKhau = edtMatKhau.getText().toString();

        if(sTenDangNhap == null || sTenDangNhap.equals(""))
        {
            Toast.makeText(this,getResources().getString(R.string.nhaptendangnhap),Toast.LENGTH_SHORT).show();
        }
        else if(sMatKhau == null || sMatKhau.equals(""))
        {
            Toast.makeText(this,getResources().getString(R.string.nhapmatkhau),Toast.LENGTH_SHORT).show();
        }else{
            nhanVienDAO = new NhanVienDAO(this);
            NhanVienDTO nhanVienDTO = new NhanVienDTO(sTenDangNhap,sMatKhau);
            int bKiemTra = nhanVienDAO.KiemTraDangNhap(nhanVienDTO);
            if(bKiemTra != 0){
                Toast.makeText(this,getResources().getString(R.string.dangnhapthanhcong),Toast.LENGTH_SHORT).show();
                Intent iTrangChu = new Intent(this,TrangChuActivity.class);
                iTrangChu.putExtra("tendangnhap",edtTenDN.getText().toString());
                iTrangChu.putExtra("manv",bKiemTra);
                startActivity(iTrangChu);
            }
            else{
                Toast.makeText(this,getResources().getString(R.string.dangnhapthatbai),Toast.LENGTH_SHORT).show();
                edtTenDN.setText("");
                edtMatKhau.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDongY :
                KiemTraDangNhap();
                ;break;
            case R.id.btnDangKy :
                Intent iDangKy = new Intent(this,DangKyActivity.class);
                startActivity(iDangKy);
                ;break;
        }
    }
}
