package com.example.sondo.app_order_food.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.R;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    LoaiMonAnDAO loaiMonAnDAO;

    EditText edtTenLoaiThucDon;
    Button btnLoaiThucDon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);

        edtTenLoaiThucDon = findViewById(R.id.edtTenLoaiThucDon);
        btnLoaiThucDon = findViewById(R.id.btnLoaiThucDon);
        btnLoaiThucDon.setOnClickListener(this);
        edtTenLoaiThucDon.setFocusable(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoaiThucDon:
                String txtLoaiThucDon = edtTenLoaiThucDon.getText().toString();
                if(txtLoaiThucDon == null || txtLoaiThucDon.equals("")){
                    Toast.makeText(this,getResources().getString(R.string.vuilongnhaptenloaimonan),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
                    loaiMonAnDTO.setTenLoai(edtTenLoaiThucDon.getText().toString());
                    loaiMonAnDAO = new LoaiMonAnDAO(this);
                    boolean kt = loaiMonAnDAO.ThemLoaiMonAn(loaiMonAnDTO);
                    Intent iThemLoaiThucDon = new Intent();
                    iThemLoaiThucDon.putExtra("ketquathem",kt);
                    setResult(Activity.RESULT_OK,iThemLoaiThucDon);
                    finish();
                }
                ;break;
        }
    }
}
