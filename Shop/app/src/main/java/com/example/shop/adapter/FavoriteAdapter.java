package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.CartActivity;
import com.example.shop.FavoriteActivity;
import com.example.shop.R;
import com.example.shop.classoop.Cart;
import com.example.shop.classoop.Favorite;
import com.example.shop.classoop.FavoriteProduct;
import com.example.shop.classoop.ListCartProduct;
import com.example.shop.classoop.ListFavoriteProduct;
import com.example.shop.module.SessionManager;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FavoriteAdapter extends BaseAdapter {

    Context context;
    ArrayList<Favorite> arrayListyeuthich;

    public FavoriteAdapter(Context context, ArrayList<Favorite> arrayListyeuthich) {
        this.context = context;
        this.arrayListyeuthich = arrayListyeuthich;
    }

    @Override
    public int getCount() {
        return arrayListyeuthich.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListyeuthich.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenyeuthich,txtgiayeuthich;
        public ImageView imgyeuthich;
        public ImageButton btDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new FavoriteAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_row_favorite,null);
            viewHolder.txttenyeuthich = convertView.findViewById(R.id.textviewtenyeuthich);
            viewHolder.txtgiayeuthich = convertView.findViewById(R.id.textviewgiayeuthich);
            viewHolder.btDelete = convertView.findViewById(R.id.buttondelete);
            viewHolder.imgyeuthich = convertView.findViewById(R.id.imageyeuthich);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (FavoriteAdapter.ViewHolder) convertView.getTag();
        }
        Favorite sanpham = (Favorite) getItem(position);
        final FavoriteAdapter.ViewHolder finalViewHolder = viewHolder;
        viewHolder.txttenyeuthich.setText(sanpham.getTensp());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiayeuthich.setText("Giá: "+ decimalFormat.format(sanpham.getGiasp())+"Đ");
        viewHolder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity.listFavoriteProduct.remove(position);
                FavoriteActivity.listFavorite.remove(position);


                SessionManager sessionManager = new SessionManager(context);
                ListFavoriteProduct listFProduct = new ListFavoriteProduct(FavoriteActivity.listFavoriteProduct);
                sessionManager.SetFavorite(listFProduct);
                FavoriteAdapter.this.notifyDataSetChanged();
            }
        });

        Picasso.get().load(sanpham.getHinhanhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgyeuthich);

        return convertView;
    }
}
