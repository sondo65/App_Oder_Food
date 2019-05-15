package com.example.sondo.app_order_food.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sondo.app_order_food.CustomAdapter.AdapterHienThiBanAn;
import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DTO.BanAnDTO;
import com.example.sondo.app_order_food.R;
import com.example.sondo.app_order_food.MainActivity.SuaBanAnActivity;
import com.example.sondo.app_order_food.MainActivity.ThemBanAnActivity;
import com.example.sondo.app_order_food.MainActivity.TrangChuActivity;
import com.example.sondo.app_order_food.MainActivity.XacNhanXoaActivity;

import java.util.ArrayList;
import java.util.List;

public class HienThiBanAnFragment extends Fragment {
    public static int REQUEST_CODE_THEM_BAN_AN = 1;

    GridView grDanhSachBanAn;
    List<BanAnDTO> dsBanAn;
    BanAnDAO banAnDAO;
    AdapterHienThiBanAn adapterHienThiBanAn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan,container,false);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.trangchu);
        //muốn class hỗ trợ menu
        setHasOptionsMenu(true);
        grDanhSachBanAn = view.findViewById(R.id.grHienThiBanAn);
        CapNhatDanhSachBanAn();
        registerForContextMenu(grDanhSachBanAn);
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
                BanAnDTO banAnDTO = dsBanAn.get(vitri);
                Intent iSuaBan = new Intent(getActivity(),SuaBanAnActivity.class);
                iSuaBan.putExtra("ban",banAnDTO);
                startActivity(iSuaBan);

                ;break;
            case R.id.mnXoa:
                AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int vitri1 = menuInfo1.position;
                BanAnDTO banAnDTO1 = dsBanAn.get(vitri1);
                Intent iXoaBan = new Intent(getActivity(),XacNhanXoaActivity.class);
                iXoaBan.putExtra("ban",banAnDTO1);
                startActivity(iXoaBan);
                ;break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        CapNhatDanhSachBanAn();
    }

    private void CapNhatDanhSachBanAn(){
        dsBanAn = new ArrayList<BanAnDTO>();
        banAnDAO = new BanAnDAO(getContext());
        dsBanAn = banAnDAO.layDanhSachBanAn();
        adapterHienThiBanAn = new AdapterHienThiBanAn(getContext(),R.layout.layout_custom_hienthibanan,dsBanAn);
        grDanhSachBanAn.setAdapter(adapterHienThiBanAn);
        adapterHienThiBanAn.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn = menu.add(1,R.id.itTrangChu_ThemBanAn,1,R.string.thembanan);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itTrangChu_ThemBanAn:
                Intent iThemBanAn = new Intent(getActivity(),ThemBanAnActivity.class);
                startActivityForResult(iThemBanAn,REQUEST_CODE_THEM_BAN_AN);
                ;break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_THEM_BAN_AN){
            if(resultCode == Activity.RESULT_OK){
                Intent intent = data;
                boolean ketQuaThem = intent.getBooleanExtra("ketquathem",false);
                if(ketQuaThem) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.thembananthanhcong), Toast.LENGTH_SHORT).show();
                    CapNhatDanhSachBanAn();
                }
                else
                    Toast.makeText(getActivity(),getResources().getString(R.string.thembananthatbai),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
