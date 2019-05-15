package com.example.sondo.app_order_food.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sondo.app_order_food.CustomAdapter.AdapterHienThiLoaiMonAn;
import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DAO.MonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.DTO.MonAnDTO;
import com.example.sondo.app_order_food.R;

import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_THEM_LOAI_THUC_DON = 202;
    public static int REQUEST_CODE_CHON_HINH = 234;
    LoaiMonAnDAO loaiMonAnDAO;
    MonAnDAO monAnDAO;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    EditText edtTenMonAn,edtGia;
    Spinner spinLoaiThucDon;
    ImageButton ibtnThemLoaiThucDon;
    ImageView imgThemHinhAnhThucDon;
    Button btnDongYThemThucDon,btnThoatThemThucDon;
    String hinhAnhMonAn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        FindView();
        HienThiLoaiMonAn();
    }

    private void HienThiLoaiMonAn(){
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        dsLoaiMonAn = loaiMonAnDAO.HienThiDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(this,R.layout.layout_custom_spinner_loaimonan,dsLoaiMonAn);
        spinLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
        adapterHienThiLoaiMonAn.notifyDataSetChanged();
    }

    private void FindView(){
        edtTenMonAn = findViewById(R.id.edtTenMonAn);
        edtGia = findViewById(R.id.edtGiaTien);
        spinLoaiThucDon = findViewById(R.id.spinLoaiThucDon);
        ibtnThemLoaiThucDon = findViewById(R.id.ibtnThemLoaiThucDon);
        imgThemHinhAnhThucDon = findViewById(R.id.imgThemHinhAnhThucDon);
        btnDongYThemThucDon = findViewById(R.id.btnDongYThemThucDon);
        btnThoatThemThucDon = findViewById(R.id.btnThoatThemThucDon);

        ibtnThemLoaiThucDon.setOnClickListener(this);
        imgThemHinhAnhThucDon.setOnClickListener(this);
        btnDongYThemThucDon.setOnClickListener(this);
        btnThoatThemThucDon.setOnClickListener(this);
        edtTenMonAn.setFocusable(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtnThemLoaiThucDon:
                Intent iThemLoaiThucDon = new Intent(ThemThucDonActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiThucDon,REQUEST_CODE_THEM_LOAI_THUC_DON);
                break;
            case R.id.imgThemHinhAnhThucDon:
                Intent intent;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }else{
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                }
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Hình ảnh món ăn"),REQUEST_CODE_CHON_HINH);
               break;
            case R.id.btnDongYThemThucDon:
                if(spinLoaiThucDon != null && spinLoaiThucDon.getSelectedItem()!=null){
                    int viTri = spinLoaiThucDon.getSelectedItemPosition();
                    int maLoai = dsLoaiMonAn.get(viTri).getMaLoai();
                    String tenMonAn = edtTenMonAn.getText().toString();
                    String giaTien = edtGia.getText().toString();
                    if(tenMonAn != null && giaTien !=null && !tenMonAn.equals("") && !giaTien.equals("")){
                        monAnDAO = new MonAnDAO(this);
                        MonAnDTO monAnDTO = new MonAnDTO(tenMonAn,giaTien,hinhAnhMonAn,maLoai);
                        boolean kq = monAnDAO.ThemMonAn(monAnDTO);
                        if(kq){
                            Toast.makeText(this,getResources().getString(R.string.themmonanthanhcong),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this,getResources().getString(R.string.themmonanthatbai),Toast.LENGTH_SHORT).show();
                        }
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                    else{
                        Toast.makeText(this,getResources().getString(R.string.vuilongnhapdayduthongtin),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,getResources().getString(R.string.banchuathemloaimonan),Toast.LENGTH_SHORT).show();
                }



                break;
            case  R.id.btnThoatThemThucDon:
                setResult(Activity.RESULT_OK);
                finish();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_THEM_LOAI_THUC_DON){
            if(resultCode == Activity.RESULT_OK){
                boolean kq = data.getBooleanExtra("ketquathem",false);
                if(kq)
                {
                    HienThiLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.themloaimonanthanhcong),Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(this,getResources().getString(R.string.themloaimonanthatbai),Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == REQUEST_CODE_CHON_HINH){
            if(resultCode == Activity.RESULT_OK){
                imgThemHinhAnhThucDon.setImageURI(data.getData());
                hinhAnhMonAn = data.getData().toString();
                imgThemHinhAnhThucDon.setBackground(null);
            }
        }
    }
}
