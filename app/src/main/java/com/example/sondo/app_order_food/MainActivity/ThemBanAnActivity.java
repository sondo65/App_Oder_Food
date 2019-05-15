package com.example.sondo.app_order_food.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.R;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtThemBanAn;
    Button btnThemBanAn;
    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edtThemBanAn = findViewById(R.id.edtTenBanAn);
        btnThemBanAn = findViewById(R.id.btnThemBanAn);
        btnThemBanAn.setOnClickListener(this);
        banAnDAO = new BanAnDAO(this);
        edtThemBanAn.setFocusable(true);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThemBanAn :
                String sTenBanAn = edtThemBanAn.getText().toString();
                if(sTenBanAn ==null || sTenBanAn.equals("")){
                    Toast.makeText(this,getResources().getString(R.string.vuilongnhaptenbanan),Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean kiemTra = banAnDAO.ThemBanAn(sTenBanAn);
                    Intent intent = new Intent();
                    intent.putExtra("ketquathem",kiemTra);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
                ;break;
        }
    }
}
