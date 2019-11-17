package com.example.shop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.CartActivity;
import com.example.shop.classoop.Cart;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.CartActivity.*;
import com.example.shop.classoop.ListCartProduct;
import com.example.shop.classoop.Product;
import com.example.shop.module.SessionManager;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> arrayListgiohang;

    public CartAdapter(Context context, ArrayList<Cart> arrayListgiohang) {
        this.context = context;
        this.arrayListgiohang = arrayListgiohang;
    }
    @Override
    public int getCount() {
        return arrayListgiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListgiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btleft,btvalue,btright;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder = new CartAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_cart,null);
            viewHolder.txttengiohang=convertView.findViewById(R.id.textviewtenmonhang);
            viewHolder.txtgiagiohang=convertView.findViewById(R.id.textviewgiamonhang);
            viewHolder.btleft=convertView.findViewById(R.id.buttonminusleft);
            viewHolder.btright=convertView.findViewById(R.id.buttonminusright);
            viewHolder.btvalue=convertView.findViewById(R.id.buttonvalue);
            viewHolder.imggiohang=convertView.findViewById(R.id.imagegiohang);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (CartAdapter.ViewHolder) convertView.getTag();
        }
        Cart sanpham= (Cart) getItem(position);
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.txttengiohang.setText(sanpham.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText("Giá: "+ decimalFormat.format(sanpham.getGiasp())+"Đ");
        viewHolder.btleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.btvalue.getText()+"");
                if( value <= 1 ){
                    //finalViewHolder.btleft.setEnabled(false);
                } else {
                    value--;
                    if(value < 2){
                        finalViewHolder.btleft.setEnabled(false);
                    }
                    if(value < 50){
                        finalViewHolder.btright.setEnabled(true);
                    }
                    finalViewHolder.btvalue.setText(value +"");
                    CartActivity.listCartProduct.get(position).setSoLuong(value);

                    SessionManager sessionManager = new SessionManager(context);
                    ListCartProduct listCartProduct = new ListCartProduct(CartActivity.listCartProduct);
                    sessionManager.SetCart(listCartProduct);
                }
            }
        });
        viewHolder.btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.btvalue.getText()+"");
                if( value >= 50 ){
                    //finalViewHolder.btright.setEnabled(false);
                } else {
                    value++;
                    if(value > 49){
                        finalViewHolder.btright.setEnabled(false);
                    }
                    if(value > 1){
                        finalViewHolder.btleft.setEnabled(true);
                    }
                    finalViewHolder.btvalue.setText(value +"");
                    CartActivity.listCartProduct.get(position).setSoLuong(value);

                    SessionManager sessionManager = new SessionManager(context);
                    ListCartProduct listCartProduct = new ListCartProduct(CartActivity.listCartProduct);
                    sessionManager.SetCart(listCartProduct);
                }
            }
        });
        viewHolder.btvalue.setText(sanpham.getSoluongsp() + "");
        Picasso.get().load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        return convertView;
    }

}
