package com.example.sondo.app_order_food.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sondo.app_order_food.CustomAdapter.AdapterHienThiMonAn;
import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DAO.GoiMonDAO;
import com.example.sondo.app_order_food.DAO.MonAnDAO;
import com.example.sondo.app_order_food.DTO.ChiTietGoiMonDTO;
import com.example.sondo.app_order_food.DTO.MonAnDTO;
import com.example.sondo.app_order_food.MainActivity.NhapSoLuongMonAnActivity;
import com.example.sondo.app_order_food.R;
import com.example.sondo.app_order_food.MainActivity.TrangChuActivity;

import java.util.List;

public class HienThiMonAnFragment extends Fragment {

    GridView grHienThiMonAn;
    AdapterHienThiMonAn adapterHienThiMonAn;
    List<MonAnDTO> dsMonAn;
    MonAnDAO monAnDAO;
    GoiMonDAO goiMonDAO;
    int maBan,maLoai;
    int maMonAn;
    int maGoiMon;
    String title = "";
    FragmentManager fragmentManager;
    BanAnDAO banAnDAO;

    public static final int REQUEST_CODE_NHAP_SOLUONG_MONAN = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_hienthi_monan,container,false);
        fragmentManager = getActivity().getSupportFragmentManager();
        grHienThiMonAn = view.findViewById(R.id.grHienThiMonAn);
        Bundle bundle = getArguments();
        goiMonDAO = new GoiMonDAO(getContext());
        banAnDAO = new BanAnDAO(getContext());
        if(bundle != null){
            maLoai = bundle.getInt("maloai");
            title = bundle.getString("tenloai");
            maBan = bundle.getInt("maban");
            maGoiMon = goiMonDAO.getMaGoiMonTheoMaBan(maBan,0);

            grHienThiMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    maMonAn = dsMonAn.get(position).getMaMon();
                    Log.d("data","ma ban la "+maBan);
                    if(maBan > 0) // nếu get được mã bàn thì mới cho hiển thị nhập số lượng
                    {
                        Intent iNhapSL = new Intent(getActivity(), NhapSoLuongMonAnActivity.class);
                        startActivityForResult(iNhapSL, REQUEST_CODE_NHAP_SOLUONG_MONAN);
                    }
                }
            });

        }

        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(title);

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    getFragmentManager().popBackStack("hienthithucdon",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });

        monAnDAO = new MonAnDAO(getContext());
        dsMonAn = monAnDAO.DanhSachMonAnTheoLoai(maLoai);
        adapterHienThiMonAn = new AdapterHienThiMonAn(getContext(),R.layout.layout_custom_hienthimonan,dsMonAn);
        grHienThiMonAn.setAdapter(adapterHienThiMonAn);
        adapterHienThiMonAn.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_NHAP_SOLUONG_MONAN){
            if(resultCode == Activity.RESULT_OK){
                int soLuong = data.getIntExtra("soluong",0);
                int soLuongHienTai = goiMonDAO.KiemTraMonAnTonTai(maGoiMon,maMonAn);
                ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO(maGoiMon,maMonAn,soLuong);
                if(soLuongHienTai != 0){ //da ton tai mon an
                    chiTietGoiMonDTO.setSoLuong(soLuong + soLuongHienTai);
                    goiMonDAO.CapNhatSoLuongMonAn(chiTietGoiMonDTO);
                    Toast.makeText(getContext(),"Đã cộng dồn số lượng !",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean kq = goiMonDAO.ThemVaoChiTietGoiMon(chiTietGoiMonDTO);
                    if (kq)
                        Toast.makeText(getContext(),"Đã thêm món ăn vào bàn số " + maBan,Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(),"Thêm thất bại !",Toast.LENGTH_SHORT).show();
                }
                //cap nhat tinh trang ban
                banAnDAO.CapNhatTinhTrangBanAn(maBan,1);
                FragmentTransaction transactionHienThiThucDon = fragmentManager.beginTransaction();
                HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
                transactionHienThiThucDon.replace(R.id.frmContent,hienThiBanAnFragment);
                transactionHienThiThucDon.commit();

            }
        }

    }
}
