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

public class NonAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListnon;

    public NonAdapter(Context context, ArrayList<Product> arrayListnon) {
        this.context = context;
        this.arrayListnon = arrayListnon;
    }

    @Override
    public int getCount() {
        return arrayListnon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListnon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttennon,txtgianon,txtmotanon;
        public ImageView imgnon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new NonAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_non,null);
            viewHolder.txttennon=convertView.findViewById(R.id.textviewtennon);
            viewHolder.txtgianon=convertView.findViewById(R.id.textviewgianon);
            viewHolder.txtmotanon=convertView.findViewById(R.id.textviewmotanon);
            viewHolder.imgnon=convertView.findViewById(R.id.imagenon);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (NonAdapter.ViewHolder) convertView.getTag();
        }
        Product sanpham= (Product) getItem(position);
        viewHolder.txttennon.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgianon.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotanon.setMaxLines(2);
        viewHolder.txtmotanon.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotanon.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgnon);
        return convertView;
    }
}
