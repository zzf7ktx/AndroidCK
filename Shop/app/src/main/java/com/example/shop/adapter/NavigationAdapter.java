package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Intent;
import androidx.cardview.widget.CardView;

import com.example.shop.KindActivity;
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
        CardView cardView;
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
            viewHolder.cardView=view.findViewById(R.id.nav_row_cardview);
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


        final ViewHolder temp = viewHolder;
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KindActivity.class);
                intent.putExtra("loai", temp.txttenLoaiSanPham.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
