package com.example.sondo.app_order_food.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.NhanVienDAO;
import com.example.sondo.app_order_food.DTO.NhanVienDTO;
import com.example.sondo.app_order_food.FragmentApp.DatePickerFragment;
import com.example.sondo.app_order_food.R;

import java.util.List;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {

    EditText edtTenDangNhap,edtMatKhau,edtNgaySinh,edtCMND;
    RadioGroup rgGioiTinh;
    Button btnDongY,btnThoat;
    String sGioiTinh ="Nam";
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        edtTenDangNhap = findViewById(R.id.edtTenDN);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtNgaySinh.setText(getResources().getString(R.string.ngaysinhmacdinh));
        edtCMND = findViewById(R.id.edtCMND);

        rgGioiTinh = findViewById(R.id.rgGioiTinh);

        btnDongY = findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(this);

        btnThoat = findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(this);

        edtNgaySinh.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDongY :
                String sTenDangNhap = edtTenDangNhap.getText().toString();
                String sMatKhau = edtMatKhau.getText().toString();

                switch (rgGioiTinh.getCheckedRadioButtonId()){
                    case R.id.radNam:
                        sGioiTinh = "Nam";
                        ;break;
                    case R.id.radNu:
                        sGioiTinh = "Nữ";
                        ;break; }

                String sNgaySinh = edtNgaySinh.getText().toString();
                String sCMND = edtCMND.getText().toString();

                if(sTenDangNhap == null || sTenDangNhap.equals(""))
                {
                    Toast.makeText(this,getResources().getString(R.string.nhaptendangnhap),Toast.LENGTH_SHORT).show();
                }
                else if(sMatKhau == null || sMatKhau.equals(""))
                {
                    Toast.makeText(this,getResources().getString(R.string.nhapmatkhau),Toast.LENGTH_SHORT).show();
                }else
                {   //Ki?m tra trùng mã
                    boolean bKiemTraTrungMa = false;
                    nhanVienDAO = new NhanVienDAO(this);
                    List<String> dsTenDangNhap = nhanVienDAO.KiemTraTrungMa(sTenDangNhap);
                    for(String tendn : dsTenDangNhap){
                        if(tendn.equals(sTenDangNhap)){
                            bKiemTraTrungMa = true;
                        }
                    }
                    if(!bKiemTraTrungMa){
                        nhanVienDAO = new NhanVienDAO(this);
                        NhanVienDTO nhanVienDTO = new NhanVienDTO(sTenDangNhap,sMatKhau,sGioiTinh,sNgaySinh,sCMND);
                        long lKiemTra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                        if(lKiemTra != 0) {
                            Toast.makeText(this, getResources().getString(R.string.dangkythanhcong), Toast.LENGTH_SHORT).show();
                            edtTenDangNhap.setText("");
                            edtMatKhau.setText("");
                            RadioButton radNam = findViewById(R.id.radNam);
                            radNam.setChecked(true);
                            edtNgaySinh.setText("");
                            edtNgaySinh.setText(getResources().getString(R.string.ngaysinhmacdinh));
                            edtCMND.setText("");
                            finish();
                        }
                        else
                            Toast.makeText(this,getResources().getString(R.string.dangkythatbai),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,getResources().getString(R.string.trungmadangnhap),Toast.LENGTH_SHORT).show();
                    }
                }
                ;break;
            case R.id.btnThoat :
                Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                startActivity(iDangNhap);
                ;break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edtNgaySinh :
                if(hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngày Sinh");
                }
                ;break;
        }
    }
}
