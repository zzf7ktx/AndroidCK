package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.classoop.Navigation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NavigationAdapter extends BaseAdapter {
    ArrayList<Navigation> arrayListloaisp;
    Context context;

    public NavigationAdapter(ArrayList<Navigation> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txttenLoaiSanPham;
        ImageView imgLoaiSp;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.activity_row_navigation,null);
            viewHolder.txttenLoaiSanPham=view.findViewById(R.id.textviewLoaisp);
            viewHolder.imgLoaiSp=view.findViewById(R.id.imageLoaisp);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Navigation loaisp= (Navigation) getItem(position);
        viewHolder.txttenLoaiSanPham.setText(loaisp.getTenloaisanpham());
        Picasso.get().load(loaisp.getHinhanhloaisanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgLoaiSp);
        return view;
    }
}
