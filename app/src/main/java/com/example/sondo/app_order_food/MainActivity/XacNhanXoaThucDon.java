package com.example.sondo.app_order_food.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.R;

public class XacNhanXoaThucDon extends AppCompatActivity implements View.OnClickListener {
    Button btnYes,btnNo;
    LoaiMonAnDAO loaiMonAnDAO;
    LoaiMonAnDTO loaiMonAnDTO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_xacnhanxoa);

        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        loaiMonAnDTO = (LoaiMonAnDTO) getIntent().getSerializableExtra("loaimonan");
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                boolean kq = loaiMonAnDAO.XoaThucDon(loaiMonAnDTO.getMaLoai());
                if(kq)
                    Toast.makeText(this,"Xoa thanh cong !",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Xoa that bai !",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }
}
