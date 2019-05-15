package com.example.sondo.app_order_food.MainActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sondo.app_order_food.FragmentApp.HienThiBanAnFragment;
import com.example.sondo.app_order_food.FragmentApp.HienThiThucDonFragment;
import com.example.sondo.app_order_food.R;

public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navTrangChu;
    DrawerLayout drawerLayout;
    Toolbar toolBar;
    TextView txtTenNhanVien_Navigation;
    FragmentManager fragmentManager;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);
        navTrangChu = findViewById(R.id.navTrangChu);
        drawerLayout = findViewById(R.id.drawLayout);
        toolBar = findViewById(R.id.toolBar);

        //Tạo ra view để include header vào navigation view
        View view = navTrangChu.inflateHeaderView(R.layout.layout_header_navigation_trangchu);
        txtTenNhanVien_Navigation = view.findViewById(R.id.txtTenNhanVien_Navigation);

        //Support cho nút 3 gạch
        setSupportActionBar(toolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tạo ra nút 3 gạch drawerToggle
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolBar,R.string.mo,R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        //Lắng nghe sự kiện click cho nút 3 gạch
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState(); // đồng bộ nút 3 gạch

        //Tạo ra màu mặc định cho các icon item
        navTrangChu.setItemIconTintList(null);
        navTrangChu.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tendangnhap");
        txtTenNhanVien_Navigation.setText(tendn);
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
        transactionHienThiBanAn.replace(R.id.frmContent,hienThiBanAnFragment);
        transactionHienThiBanAn.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.itTrangChu:
                FragmentTransaction transactionHienThiBanAn = fragmentManager.beginTransaction();
                HienThiBanAnFragment hienThiBanAnFragment = new HienThiBanAnFragment();
                transactionHienThiBanAn.replace(R.id.frmContent,hienThiBanAnFragment);
                transactionHienThiBanAn.commit();

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                ;break;

            case R.id.itThucDon:

                FragmentTransaction transactionHienThiThucDon = fragmentManager.beginTransaction();
                HienThiThucDonFragment hienThiThucDonFragment = new HienThiThucDonFragment();
                transactionHienThiThucDon.replace(R.id.frmContent,hienThiThucDonFragment);
                transactionHienThiThucDon.commit();

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                    ;break;
            case R.id.itThongKe:

                ;break;
            case R.id.itNhanVien:

                ;break;
            case R.id.itCaiDat:

                ;break;
        }
        return false;
    }
}
