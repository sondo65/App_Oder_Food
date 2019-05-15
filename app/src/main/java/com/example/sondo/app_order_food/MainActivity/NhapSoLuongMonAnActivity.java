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

import com.example.sondo.app_order_food.DAO.GoiMonDAO;
import com.example.sondo.app_order_food.DAO.MonAnDAO;
import com.example.sondo.app_order_food.R;

public class NhapSoLuongMonAnActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSoLuong;
    Button btnThemSoLuong;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nhapsoluongmonan);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThemSoLuong = findViewById(R.id.btnThemSoLuong);
        btnThemSoLuong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThemSoLuong:
                if(edtSoLuong.getText().toString() != null && !edtSoLuong.getText().toString().equals(""))
                {
                    int soluong = Integer.parseInt(edtSoLuong.getText().toString());
                    if (soluong > 0)
                    {
                        Intent iBack = new Intent();
                        iBack.putExtra("soluong",soluong);
                        setResult(Activity.RESULT_OK,iBack);
                        finish();
                    }
                    else
                        Toast.makeText(this,getResources().getString(R.string.soluongphailonhonkhong),Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(this,getResources().getString(R.string.vuilongnhapdayduthongtin),Toast.LENGTH_SHORT).show();
                }

                ;break;
        }
    }
}
