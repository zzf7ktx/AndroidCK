package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.classoop.Message;

import java.util.ArrayList;

public class AdapterMessage extends BaseAdapter {
    ArrayList<Message> list;
    Context context;
    Integer id;
    LayoutInflater li;

    public AdapterMessage(ArrayList<Message> list, Context context, Integer id) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = li.inflate(R.layout.activity_row_mess, null);
            viewHolder = new ViewHolder();
            viewHolder.txtUser = convertView.findViewById(R.id.messrow_txt_user);
            viewHolder.txtDetail = convertView.findViewById(R.id.messrow_txt_detail);
            viewHolder.txtTime = convertView.findViewById(R.id.messrow_txt_time);
            viewHolder.layout = convertView.findViewById(R.id.messrow_background);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = list.get(position);
        viewHolder.txtDetail.setText(message.getDetailMess());
        viewHolder.txtTime.setText(message.getTimeSend().toString().substring(0, 16).replace('T', ' '));
        if(message.isAdminSend()){
            viewHolder.txtUser.setText(id.toString());
            viewHolder.layout.setBackgroundColor(0xffffff);
        } else{
            viewHolder.txtUser.setText("Admin");
        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtUser, txtDetail, txtTime;
        LinearLayout layout;
    }
}
