package com.example.sondo.app_order_food.CustomAdapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sondo.app_order_food.DTO.MonAnDTO;
import com.example.sondo.app_order_food.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterHienThiMonAn extends BaseAdapter {

    Context context;
    int layout;
    List<MonAnDTO> dsMonAn;
    ViewHolderHienThiMonAn viewHolderHienThiMonAn;
    public  AdapterHienThiMonAn(Context context, int layout, List<MonAnDTO> dsMonAn){
        this.context = context;
        this.layout = layout;
        this.dsMonAn = dsMonAn;
    }
    @Override
    public int getCount() {
        return dsMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return dsMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dsMonAn.get(position).getMaMon();
    }

    private class ViewHolderHienThiMonAn{
        ImageView imgHinhAnhMonAn;
        TextView txtTenMonAn,txtGiaTien;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);
            viewHolderHienThiMonAn = new ViewHolderHienThiMonAn();
            viewHolderHienThiMonAn.imgHinhAnhMonAn = view.findViewById(R.id.imgHinhAnhMonAn);
            viewHolderHienThiMonAn.txtTenMonAn = view.findViewById(R.id.txtTenMonAn);
            viewHolderHienThiMonAn.txtGiaTien = view.findViewById(R.id.txtGiaTien);
            view.setTag(viewHolderHienThiMonAn);
        }
        else{
            viewHolderHienThiMonAn = (ViewHolderHienThiMonAn) view.getTag();
        }
        MonAnDTO monAnDTO =  dsMonAn.get(position);
        String strHinhAnh = monAnDTO.getHinhAnh();
        if(strHinhAnh !=null && !strHinhAnh.equals(""))
        {
            Uri uri = Uri.parse(strHinhAnh);
            viewHolderHienThiMonAn.imgHinhAnhMonAn.setImageURI(uri);
        }
        viewHolderHienThiMonAn.txtTenMonAn.setText(monAnDTO.getTenMon().toString());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String strGiaTien = currencyVN.format(Long.parseLong(monAnDTO.getGiaTien()));
        viewHolderHienThiMonAn.txtGiaTien.setText(strGiaTien);
        return view;
    }
}
