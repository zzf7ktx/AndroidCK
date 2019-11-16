package com.example.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.classoop.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KindAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListKind;

    public KindAdapter(Context context, ArrayList<Product> arrayListKind) {
        this.context = context;
        this.arrayListKind = arrayListKind;
    }

    @Override
    public int getCount() {
        return arrayListKind.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListKind.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txttenKind,txtgiaKind,txtmotaKind;
        public ImageView imgKind;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KindAdapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new KindAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_kind,null);
            viewHolder.txttenKind=convertView.findViewById(R.id.textviewtenKind);
            viewHolder.txtgiaKind=convertView.findViewById(R.id.textviewgiaKind);
            viewHolder.txtmotaKind=convertView.findViewById(R.id.textviewmotaKind);
            viewHolder.imgKind=convertView.findViewById(R.id.imageKind);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (KindAdapter.ViewHolder) convertView.getTag();
        }
        Product sanpham= (Product) getItem(position);
        viewHolder.txttenKind.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiaKind.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotaKind.setMaxLines(2);
        viewHolder.txtmotaKind.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaKind.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgKind);
        return convertView;
    }
}
