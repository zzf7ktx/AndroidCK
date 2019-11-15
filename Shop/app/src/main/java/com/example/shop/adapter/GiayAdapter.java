package com.example.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.classoop.Product;
import com.example.shop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiayAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListgiay;

    public GiayAdapter(Context context, ArrayList<Product> arrayListgiay) {
        this.context = context;
        this.arrayListgiay = arrayListgiay;
    }

    @Override
    public int getCount() {
        return arrayListgiay.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListgiay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txttengiay,txtgiagiay,txtmotagiay;
        public ImageView imggiay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_giay,null);
            viewHolder.txttengiay=convertView.findViewById(R.id.textviewtengiay);
            viewHolder.txtgiagiay=convertView.findViewById(R.id.textviewgiagiay);
            viewHolder.txtmotagiay=convertView.findViewById(R.id.textviewmotagiay);
            viewHolder.imggiay=convertView.findViewById(R.id.imagegiay);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Product sanpham= (Product) getItem(position);
        viewHolder.txttengiay.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiagiay.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotagiay.setMaxLines(2);
        viewHolder.txtmotagiay.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotagiay.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiay);
        return convertView;
    }
}
