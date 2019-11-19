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

import androidx.cardview.widget.CardView;

import com.example.shop.DetailsActivity;
import com.example.shop.OrderActivity;
import com.example.shop.R;
import com.example.shop.classoop.Order;
import com.example.shop.classoop.OrderProduct;
import com.example.shop.classoop.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<OrderProduct> arrayList;

    public OrderAdapter(Context context, ArrayList<OrderProduct> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView txtten,txtgia,txtsoluong;
        public ImageView imgOrder;
        public CardView cardView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAdapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new OrderAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_order,null);
            viewHolder.txtten=convertView.findViewById(R.id.textviewtensanphamOrderProduct);
            viewHolder.txtgia=convertView.findViewById(R.id.textviewgiasanphamOrderProduct);
            viewHolder.txtsoluong=convertView.findViewById(R.id.textviewsoluongOrderProduct);
            viewHolder.cardView=convertView.findViewById(R.id.order_product_row_cardview);
            viewHolder.imgOrder=convertView.findViewById(R.id.imageOrderProduct);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (OrderAdapter.ViewHolder) convertView.getTag();
        }
        OrderProduct sanpham = (OrderProduct) getItem(position);
        viewHolder.txtten.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgia.setText("Tổng giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtsoluong.setText("Số lượng: " + String.valueOf(sanpham.getSoluong()));

        final int temp = sanpham.getMasanpham();
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
                .into(viewHolder.imgOrder);
        return convertView;
    }
}
