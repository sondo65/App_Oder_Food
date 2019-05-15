package com.example.sondo.app_order_food.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.BanAnDAO;
import com.example.sondo.app_order_food.DAO.GoiMonDAO;

import com.example.sondo.app_order_food.DTO.BanAnDTO;
import com.example.sondo.app_order_food.DTO.GoiMonDTO;
import com.example.sondo.app_order_food.FragmentApp.HienThiThucDonFragment;
import com.example.sondo.app_order_food.R;
import com.example.sondo.app_order_food.MainActivity.ThanhToanActivity;
import com.example.sondo.app_order_food.MainActivity.TrangChuActivity;


import java.util.Calendar;
import java.util.List;

public class AdapterHienThiBanAn extends BaseAdapter implements View.OnClickListener{

    Context context;
    int layout;
    List<BanAnDTO> dsBanAn;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;
    GoiMonDAO goiMonDAO;
    FragmentManager fragmentManager;
    private boolean flagAnHien = false;
    int maBan;
    int vitri = 0;

    public  AdapterHienThiBanAn(Context context, int layout, List<BanAnDTO> dsBanAn){
        this.context = context;
        this.layout = layout;
        this.dsBanAn = dsBanAn;
        banAnDAO = new BanAnDAO(context);
        fragmentManager = ((TrangChuActivity)context).getSupportFragmentManager();
    }
    @Override
    public int getCount() {
        return dsBanAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsBanAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  dsBanAn.get(position).getMaBan();
    }

    private class ViewHolderBanAn{
        ImageView imgBanAn,imgGoiMon,imgThanhToan,imgAnButton;
        TextView txtTenBanAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_custom_hienthibanan,parent,false);
            viewHolderBanAn = new ViewHolderBanAn();
            viewHolderBanAn.imgBanAn = view.findViewById(R.id.imgBanAn);
            viewHolderBanAn.imgGoiMon = view.findViewById(R.id.imgGoiMon);
            viewHolderBanAn.imgThanhToan = view.findViewById(R.id.imgThanhToan);
            viewHolderBanAn.imgAnButton = view.findViewById(R.id.imgAnButton);
            viewHolderBanAn.txtTenBanAn = view.findViewById(R.id.txtTenBanAn);
            view.setTag(viewHolderBanAn);
        }else {
            viewHolderBanAn = (ViewHolderBanAn) view.getTag();
        }

        if(dsBanAn.get(position).isClick()){
            HienThiNutBanAn();
        }
        //luu vi tri position khi imageview duoc tao ra
        viewHolderBanAn.imgBanAn.setTag(position);
        BanAnDTO banAnDTO = dsBanAn.get(position);
        Log.d("son","maban "+banAnDTO.getMaBan()+"");
        boolean tinhTrang = banAnDAO.getTinhTrangBanAn(banAnDTO.getMaBan());
        if (tinhTrang) {
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banantrue);
            Animation animRotate = AnimationUtils.loadAnimation(context,R.anim.hieuung_xoay_banan);
            viewHolderBanAn.imgBanAn.startAnimation(animRotate);
        }
        else
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banan);

        viewHolderBanAn.txtTenBanAn.setText(banAnDTO.getTenBan());



        viewHolderBanAn.imgGoiMon.setOnClickListener(this);
        viewHolderBanAn.imgAnButton.setOnClickListener(this);
        viewHolderBanAn.imgThanhToan.setOnClickListener(this);
        viewHolderBanAn.imgBanAn.setOnClickListener(this);

        return  view;
    }

    private void HienThiNutBanAn(){
        viewHolderBanAn.imgGoiMon.setVisibility(View.VISIBLE);
        viewHolderBanAn.imgThanhToan.setVisibility(View.VISIBLE);
        viewHolderBanAn.imgAnButton.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(context,R.anim.hieuung_button_banan);
        viewHolderBanAn.imgGoiMon.startAnimation(anim);
        viewHolderBanAn.imgThanhToan.startAnimation(anim);
        viewHolderBanAn.imgAnButton.startAnimation(anim);
    }

    private void AnNutBanAn(){
        viewHolderBanAn.imgGoiMon.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imgThanhToan.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imgAnButton.setVisibility(View.INVISIBLE);

        Animation anim = AnimationUtils.loadAnimation(context,R.anim.hieuung_an_button_banan);
        viewHolderBanAn.imgGoiMon.startAnimation(anim);
        viewHolderBanAn.imgThanhToan.startAnimation(anim);
        viewHolderBanAn.imgAnButton.startAnimation(anim);
    }

    @Override
    public void onClick(View v) {
        viewHolderBanAn = (ViewHolderBanAn) ((View)v.getParent()).getTag();
        switch (v.getId()){
            case R.id.imgBanAn:
                flagAnHien = true;
                int viTri = (int) v.getTag();
                dsBanAn.get(viTri).setClick(true);
                HienThiNutBanAn();
                ;break;
            case R.id.imgGoiMon:
                GoiMon();
                ;break;
            case R.id.imgAnButton:
                AnNutBanAn();
                ;break;
            case R.id.imgThanhToan:
                ThanhToan();
                ;break;
        }
    }


    private void GoiMon(){
        vitri = (int) viewHolderBanAn.imgBanAn.getTag();
        maBan = dsBanAn.get(vitri).getMaBan();
        Log.d("vitri","vi tri la " + vitri);

        //Kiemtra xem btinh trang ban da co nguoi ngoi chua
        banAnDAO = new BanAnDAO(context);
        Boolean daCoKhach = banAnDAO.getTinhTrangBanAn(maBan);
        Log.d("tinhtrang","tinh trang la " + daCoKhach);
        //neu chua thi them vao bang goi mon va cap nhat lai tinh trang ban
        if(!daCoKhach){

            //them vao bang goi mon
            int manv = ((TrangChuActivity)context).getIntent().getIntExtra("manv",0);
            Calendar calendar = Calendar.getInstance();
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
            String ngayThanhToan = dateFormat.format(calendar.getTime());
            GoiMonDTO goiMonDTO = new GoiMonDTO(manv,ngayThanhToan,false,maBan);
            goiMonDAO = new GoiMonDAO(context);
            boolean kq = goiMonDAO.GoiMon(goiMonDTO);
            if(kq)
                Toast.makeText(context,context.getResources().getString(R.string.batdaugoimon),Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,context.getResources().getString(R.string.goimonthatbai),Toast.LENGTH_SHORT).show();
        }
        //goi toi fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("maban",maBan);
        hienThiThucDonFragment.setArguments(bundle);
        transaction.replace(R.id.frmContent,hienThiThucDonFragment).addToBackStack("hienthibanan");
        transaction.commit();
    }

    private void ThanhToan(){
        vitri = (int) viewHolderBanAn.imgBanAn.getTag();
        maBan = dsBanAn.get(vitri).getMaBan();
        Log.d("data","vitri la "+ vitri);
        boolean tinhTrang = banAnDAO.getTinhTrangBanAn(maBan);
        if(tinhTrang){
            Intent iThanhToan = new Intent(context,ThanhToanActivity.class);
            Log.d("data","maban la "+ maBan);
            iThanhToan.putExtra("maban",maBan);
            context.startActivity(iThanhToan);
        }
        else{
            Toast.makeText(context,"Bàn chưa có khách !",Toast.LENGTH_SHORT).show();
        }
    }
}
