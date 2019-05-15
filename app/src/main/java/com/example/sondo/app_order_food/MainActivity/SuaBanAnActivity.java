package com.example.sondo.app_order_food.MainActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DTO.BanAnDTO;
import com.example.sondo.app_order_food.R;

public class SuaBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSuaBanAn;
    Button btnSuaBanAn;
    BanAnDTO banAnDTO;
    BanAnDAO banAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sua_banan);
        banAnDAO = new BanAnDAO(this);
        edtSuaBanAn = findViewById(R.id.edtSuaBanAn);
        btnSuaBanAn = findViewById(R.id.btnSuaBanAn);
        btnSuaBanAn.setOnClickListener(this);
        banAnDTO = (BanAnDTO) getIntent().getSerializableExtra("ban");
        edtSuaBanAn.setText(banAnDTO.getTenBan());
        Log.d("son","ten ban "+banAnDTO.getTenBan());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSuaBanAn:
                String sTenBanAn = edtSuaBanAn.getText().toString();
                if(sTenBanAn ==null || sTenBanAn.equals("")){
                    Toast.makeText(this,getResources().getString(R.string.vuilongnhaptenbanan),Toast.LENGTH_SHORT).show();
                }
                else {
                        banAnDTO.setTenBan(sTenBanAn);
                        boolean kq = banAnDAO.SuaBanAn(banAnDTO);
                        if(kq)
                            Toast.makeText(this,"Sua thanh cong !",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this,"Sua that bai !",Toast.LENGTH_SHORT).show();

                        finish();
                }
                ;break;
        }
    }
}
