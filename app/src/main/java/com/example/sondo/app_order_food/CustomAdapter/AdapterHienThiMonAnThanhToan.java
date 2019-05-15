package com.example.sondo.app_order_food.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sondo.app_order_food.DTO.ThanhToanDTO;
import com.example.sondo.app_order_food.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterHienThiMonAnThanhToan extends BaseAdapter {

    Context context;
    int layout;
    List<ThanhToanDTO> dsThanhToan;
    ViewHolderMonAnThanhToan viewHolderMonAnThanhToan;
    public  AdapterHienThiMonAnThanhToan(Context context, int layout, List<ThanhToanDTO> dsThanhToan){
        this.context = context;
        this.layout = layout;
        this.dsThanhToan = dsThanhToan;
        viewHolderMonAnThanhToan = new ViewHolderMonAnThanhToan();
    }

    @Override
    public int getCount() {
        return dsThanhToan.size();
    }

    @Override
    public Object getItem(int position) {
        return dsThanhToan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolderMonAnThanhToan{
       public TextView txtTenMonAn_ThanhToan,txtSoLuongThanhToan,txtGiaTienThanhToan;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null,false);

            viewHolderMonAnThanhToan.txtTenMonAn_ThanhToan = view.findViewById(R.id.txtTenMonAn_ThanhToan);
            viewHolderMonAnThanhToan.txtSoLuongThanhToan = view.findViewById(R.id.txtSoLuong_ThanhToan);
            viewHolderMonAnThanhToan.txtGiaTienThanhToan = view.findViewById(R.id.txtGiaTien_ThanhToan);

            view.setTag(viewHolderMonAnThanhToan);
        }
        else{
            viewHolderMonAnThanhToan = (ViewHolderMonAnThanhToan) view.getTag();
        }
        ThanhToanDTO thanhToanDTO = dsThanhToan.get(position);
        viewHolderMonAnThanhToan.txtTenMonAn_ThanhToan.setText(thanhToanDTO.getTenMonAn());
        viewHolderMonAnThanhToan.txtSoLuongThanhToan.setText(String.valueOf(thanhToanDTO.getSoLuong()));
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String strGiaTien = currencyVN.format(thanhToanDTO.getGiaTien());
        viewHolderMonAnThanhToan.txtGiaTienThanhToan.setText(strGiaTien);

        return view;
    }
}
