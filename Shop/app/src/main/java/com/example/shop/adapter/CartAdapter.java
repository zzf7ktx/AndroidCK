package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.classoop.Cart;
import com.example.shop.MainActivity;
import com.example.shop.R;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
