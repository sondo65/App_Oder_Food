package com.example.sondo.app_order_food.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DTO.BanAnDTO;
import com.example.sondo.app_order_food.R;

public class XacNhanXoaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnYes,btnNo;
    BanAnDTO banAnDTO;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_xacnhanxoa);
        banAnDAO = new BanAnDAO(this);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        banAnDTO = (BanAnDTO) getIntent().getSerializableExtra("ban");
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                boolean kq = banAnDAO.XoaBanAn(banAnDTO.getMaBan());
                if(kq)
                    Toast.makeText(this,R.string.xoathanhcong,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,R.string.xoathatbai,Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_no:
                finish();
                break;
        }
    }
}
