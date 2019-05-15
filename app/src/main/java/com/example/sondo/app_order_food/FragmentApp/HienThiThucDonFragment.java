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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.sondo.app_order_food.CustomAdapter.AdapterHienThiLoaiMonAnTrongThucDon;
import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.MainActivity.XacNhanXoaThucDon;
import com.example.sondo.app_order_food.R;
import com.example.sondo.app_order_food.MainActivity.SuaThucDonActivity;
import com.example.sondo.app_order_food.MainActivity.ThemThucDonActivity;
import com.example.sondo.app_order_food.MainActivity.TrangChuActivity;

import java.util.List;

public class HienThiThucDonFragment extends Fragment{

    public static int REQUEST_CODE_THEM_THUC_DON = 302;
    GridView grHienThiThucDon;
    AdapterHienThiLoaiMonAnTrongThucDon adapterHienThiLoaiMonAnTrongThucDon;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;
    FragmentManager fragmentManager;
    int maBan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthithucdon,container,false);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thucdon);
        setHasOptionsMenu(true);
        grHienThiThucDon = view.findViewById(R.id.grHienThiThucDon);
        loaiMonAnDAO = new LoaiMonAnDAO(getActivity());
        CapNhatThucDon();
        fragmentManager = getActivity().getSupportFragmentManager();

        Bundle bundle = getArguments();
        if (bundle != null)
        {
            maBan = bundle.getInt("maban");
        }
        else{
            Log.d("data","ma ban is null");
        }
        grHienThiThucDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maLoai = dsLoaiMonAn.get(position).getMaLoai();
                String tenLoai =dsLoaiMonAn.get(position).getTenLoai();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai",maLoai);
                bundle.putString("tenloai",tenLoai);
                bundle.putInt("maban",maBan);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                HienThiMonAnFragment hienThiMonAnFragment = new HienThiMonAnFragment();
                hienThiMonAnFragment.setArguments(bundle);
                transaction.replace(R.id.frmContent,hienThiMonAnFragment).addToBackStack("hienthithucdon");
                transaction.commit();
            }
        });



        registerForContextMenu(grHienThiThucDon);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSua:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int vitri = menuInfo.position;
                LoaiMonAnDTO loaiMonAnDTO = dsLoaiMonAn.get(vitri);
                Intent iSua = new Intent(getActivity(), SuaThucDonActivity.class);
                iSua.putExtra("loaimonan",loaiMonAnDTO);
                startActivity(iSua);
                ;break;
            case R.id.mnXoa:
                AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int vitri1 = menuInfo1.position;
                LoaiMonAnDTO loaiMonAnDTO1 = dsLoaiMonAn.get(vitri1);
                Intent iXoa = new Intent(getActivity(), XacNhanXoaThucDon.class);
                iXoa.putExtra("loaimonan",loaiMonAnDTO1);
                startActivity(iXoa);
                ;break;
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        CapNhatThucDon();
    }

    public void CapNhatThucDon(){
        dsLoaiMonAn = loaiMonAnDAO.HienThiDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAnTrongThucDon = new AdapterHienThiLoaiMonAnTrongThucDon(getActivity(),R.layout.layout_custom_hienthiloaimonan,dsLoaiMonAn);
        grHienThiThucDon.setAdapter(adapterHienThiLoaiMonAnTrongThucDon);
        adapterHienThiLoaiMonAnTrongThucDon.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemMonAn = menu.add(1,R.id.itTrangChu_ThemThucDon,1,R.string.themthucdon);
        itThemMonAn.setIcon(R.drawable.logodangnhap);
        itThemMonAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itTrangChu_ThemThucDon:
                //Toast.makeText(getActivity(),"click",Toast.LENGTH_SHORT).show();
                Intent iThemThucDon = new Intent(getActivity(),ThemThucDonActivity.class);
                startActivityForResult(iThemThucDon,REQUEST_CODE_THEM_THUC_DON);
                ;break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_THEM_THUC_DON){
            if(resultCode == Activity.RESULT_OK){
                CapNhatThucDon();
            }
        }
    }

}
