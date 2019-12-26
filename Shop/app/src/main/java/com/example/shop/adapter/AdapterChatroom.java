package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;


import com.example.shop.ChatActivity;
import com.example.shop.R;

import java.util.ArrayList;

public class AdapterChatroom extends BaseAdapter {
    private ArrayList<Integer> list;
    private Context context;
    private LayoutInflater li;

    public AdapterChatroom(Context context, ArrayList<Integer> list) {
        this.list = list;
        this.context = context;
        this.li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public void setList(ArrayList<Integer> list){
//        this.list = list;
//    }

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
            convertView = li.inflate(R.layout.activity_row_chatroom, null);
            viewHolder = new ViewHolder();
            viewHolder.button = convertView.findViewById(R.id.chatroom_row_btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Integer temp = list.get(position);
        viewHolder.button.setText(temp.toString());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("id", 1);
                intent.putExtra("idSendTo", temp);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        Button button;
    }
}
