package com.example.sondo.app_order_food.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sondo.app_order_food.DAO.LoaiMonAnDAO;
import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.R;

import java.util.List;

public class AdapterHienThiLoaiMonAnTrongThucDon extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    ViewHolderHienThiLoaiMonAn viewHolderHienThiLoaiMonAn;
    LoaiMonAnDAO loaiMonAnDAO;
    public AdapterHienThiLoaiMonAnTrongThucDon(Context context, int layout, List<LoaiMonAnDTO> dsLoaiMonAn){
        this.context = context;
        this.layout = layout;
        this.dsLoaiMonAn = dsLoaiMonAn;
        loaiMonAnDAO = new LoaiMonAnDAO(context);
    }
    @Override
    public int getCount() {
        return dsLoaiMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsLoaiMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsLoaiMonAn.get(position).getMaLoai();
    }

    private class ViewHolderHienThiLoaiMonAn{
        ImageView imgImageMonAn;
        TextView txtTenLoaiMonAn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);
            viewHolderHienThiLoaiMonAn = new ViewHolderHienThiLoaiMonAn();
            viewHolderHienThiLoaiMonAn.imgImageMonAn = view.findViewById(R.id.imgImageMonAn);
            viewHolderHienThiLoaiMonAn.txtTenLoaiMonAn = view.findViewById(R.id.txtTenLoaiMonAn);
            view.setTag(viewHolderHienThiLoaiMonAn);
        }
        else{
            viewHolderHienThiLoaiMonAn = (ViewHolderHienThiLoaiMonAn) view.getTag();
        }

        LoaiMonAnDTO loaiMonAnDTO = dsLoaiMonAn.get(position);
        int maLoai = loaiMonAnDTO.getMaLoai();
        String hinhAnh = loaiMonAnDAO.getHinhAnhMonAnBangMaLoai(maLoai);
        if(hinhAnh != null && !hinhAnh.equals(""))
        {
            Uri uriHinhAnh = Uri.parse(hinhAnh);
            viewHolderHienThiLoaiMonAn.imgImageMonAn.setImageURI(uriHinhAnh);
        }
        viewHolderHienThiLoaiMonAn.txtTenLoaiMonAn.setText(loaiMonAnDTO.getTenLoai());

        return view;
    }
}
