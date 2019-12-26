package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.shop.DetailsActivity;
import com.example.shop.R;
import com.example.shop.classoop.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KindAdapter extends BaseAdapter {
    Context context;
    ArrayList<Product> arrayListKind;
    int order = 0;

    public KindAdapter(Context context, ArrayList<Product> arrayListKind, int order) {
        this.context = context;
        this.arrayListKind = arrayListKind;
        this.order = order;
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
        public CardView cardView;
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
            viewHolder.cardView=convertView.findViewById(R.id.kind_row_cardview);
            viewHolder.imgKind=convertView.findViewById(R.id.imageKind);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (KindAdapter.ViewHolder) convertView.getTag();
        }
        Product sanpham;
        if (order == 1)
        {
            sanpham = (Product) getItem(position);
        }
        else
        {
            sanpham = (Product) getItem(getCount() - position - 1);
        }
        viewHolder.txttenKind.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiaKind.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotaKind.setMaxLines(2);
        viewHolder.txtmotaKind.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaKind.setText(sanpham.getMotasanpham());

        final int temp = sanpham.getID();
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id", temp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgKind);
        return convertView;
    }
}
