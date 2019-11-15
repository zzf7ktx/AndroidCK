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

public class DepAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListdep;

    public DepAdapter(Context context, ArrayList<Product> arrayListdep) {
        this.context = context;
        this.arrayListdep = arrayListdep;
    }

    @Override
    public int getCount() {
        return arrayListdep.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListdep.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txttendep,txtgiadep,txtmotadep;
        public ImageView imgdep;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DepAdapter.ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder=new DepAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_dep,null);
            viewHolder.txttendep=convertView.findViewById(R.id.textviewtendep);
            viewHolder.txtgiadep=convertView.findViewById(R.id.textviewgiadep);
            viewHolder.txtmotadep=convertView.findViewById(R.id.textviewmotadep);
            viewHolder.imgdep=convertView.findViewById(R.id.imagedep);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (DepAdapter.ViewHolder) convertView.getTag();
        }
        Product sanpham= (Product) getItem(position);
        viewHolder.txttendep.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiadep.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotadep.setMaxLines(2);
        viewHolder.txtmotadep.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotadep.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgdep);
        return convertView;
    }
}
