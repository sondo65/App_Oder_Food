package com.example.sondo.app_order_food.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sondo.app_order_food.DTO.LoaiMonAnDTO;
import com.example.sondo.app_order_food.R;

import java.util.List;


public class AdapterHienThiLoaiMonAn extends BaseAdapter {
    Context context;
    int layout;
    List<LoaiMonAnDTO> dsLoaiMonAn;
    ViewHolderHienThiLoaiMonAn viewHolderHienThiLoaiMonAn;
    public AdapterHienThiLoaiMonAn (Context context, int layout, List<LoaiMonAnDTO> dsLoaiMonAn){
        this.context = context;
        this.layout = layout;
        this.dsLoaiMonAn = dsLoaiMonAn;
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
        TextView txtHienThiLoaiMonAn;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_custom_spinner_loaimonan,parent,false);
            viewHolderHienThiLoaiMonAn = new ViewHolderHienThiLoaiMonAn();
            viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn = view.findViewById(R.id.txtTenLoaiMonAn);
            view.setTag(viewHolderHienThiLoaiMonAn);
        }
        else{
            viewHolderHienThiLoaiMonAn = (ViewHolderHienThiLoaiMonAn) view.getTag();
        }
        viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn.setText(dsLoaiMonAn.get(position).getTenLoai());
        viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn.setTag(dsLoaiMonAn.get(position).getMaLoai());

        return view;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_custom_spinner_loaimonan,parent,false);
            viewHolderHienThiLoaiMonAn = new ViewHolderHienThiLoaiMonAn();
            viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn = view.findViewById(R.id.txtTenLoaiMonAn);
            view.setTag(viewHolderHienThiLoaiMonAn);
        }
        else{
            viewHolderHienThiLoaiMonAn = (ViewHolderHienThiLoaiMonAn) view.getTag();
        }
        viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn.setText(dsLoaiMonAn.get(position).getTenLoai());
        viewHolderHienThiLoaiMonAn.txtHienThiLoaiMonAn.setTag(dsLoaiMonAn.get(position).getMaLoai());

        return view;
    }
}
