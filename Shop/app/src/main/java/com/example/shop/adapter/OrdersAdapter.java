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
import com.example.shop.classoop.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdersAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> arrayList;

    public OrdersAdapter(Context context, ArrayList<Order> arrayList) {
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
        public TextView txttenOrder,txtgiaOrder,txtdiachiOrder, txttrangthaiOrder;
        public CardView cardView;
        public ImageView imgOrder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrdersAdapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new OrdersAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_orders,null);
            viewHolder.txttenOrder=convertView.findViewById(R.id.textviewtenOrder);
            viewHolder.txtgiaOrder=convertView.findViewById(R.id.textviewgiaOrder);
            viewHolder.txtdiachiOrder=convertView.findViewById(R.id.textviewdiachiOrder);
            viewHolder.cardView=convertView.findViewById(R.id.order_row_cardview);
            viewHolder.txttrangthaiOrder=convertView.findViewById(R.id.textviewtrangthaiOrder);
            viewHolder.imgOrder=convertView.findViewById(R.id.imageOrder);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (OrdersAdapter.ViewHolder) convertView.getTag();
        }
        Order order= (Order) getItem(position);
        viewHolder.txttenOrder.setText("#" + String.valueOf(order.getID()) + " Ngay tao: " + order.getNgaytao());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiaOrder.setText("Tổng cộng: "+ decimalFormat.format(order.getTongtien())+"Đ");
        viewHolder.txtdiachiOrder.setMaxLines(2);
        viewHolder.txtdiachiOrder.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtdiachiOrder.setText(order.getDiachi());

        switch (order.getTrangthai())
        {
            case 1:
                viewHolder.txttrangthaiOrder.setText("Đặt hàng");
                break;
            case 2:
                viewHolder.txttrangthaiOrder.setText("Đang xử lý");
                break;
            case 3:
                viewHolder.txttrangthaiOrder.setText("Đang vận chuyển");
                break;
            case 4:
                viewHolder.txttrangthaiOrder.setText("Đã giao");
                viewHolder.txttrangthaiOrder.setTextColor(context.getResources().getColor(R.color.done));
                break;
            case 0:
                viewHolder.txttrangthaiOrder.setText("Đã hủy");
                break;
        }

        final int temp = order.getID();
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("idDonHang", temp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        Picasso.get().load(order.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgOrder);
        return convertView;
    }
}
