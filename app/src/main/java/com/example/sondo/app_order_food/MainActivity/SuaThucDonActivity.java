package com.example.sondo.app_order_food.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.R;

public class SuaThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSua;
    Button btnSua;
    LoaiMonAnDAO loaiMonAnDAO;
    LoaiMonAnDTO loaiMonAnDTO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sua_banan);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        edtSua = findViewById(R.id.edtSuaBanAn);
        btnSua = findViewById(R.id.btnSuaBanAn);

        loaiMonAnDTO = (LoaiMonAnDTO) getIntent().getSerializableExtra("loaimonan");

        edtSua.setText(loaiMonAnDTO.getTenLoai());
        btnSua.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSuaBanAn:
                loaiMonAnDTO.setTenLoai(edtSua.getText().toString());
                boolean kq = loaiMonAnDAO.SuaThucDon(loaiMonAnDTO);
                if(kq)
                    Toast.makeText(SuaThucDonActivity.this,"Sua thanh cong !",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SuaThucDonActivity.this,"Sua that bai !",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
