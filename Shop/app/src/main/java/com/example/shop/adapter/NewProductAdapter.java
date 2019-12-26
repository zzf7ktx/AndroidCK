package com.example.shop.adapter;

import com.example.shop.module.CheckConnect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.DetailsActivity;
import com.example.shop.R;
import com.example.shop.classoop.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ITemHolder> {

    Context context;
    ArrayList<Product> arraysanpham;

    public NewProductAdapter(Context context, ArrayList<Product> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ITemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_product,null);
        ITemHolder iTemHolder=new ITemHolder(v);

        return iTemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ITemHolder holder, int position) {
        Product sanpham =arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText( decimalFormat.format(sanpham.getGiasanpham())+"VNĐ");
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }


    public class ITemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;

        public ITemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham =itemView.findViewById(R.id.imgsanpham);
            txtgiasanpham =itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham=itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, DetailsActivity.class);
                    intent.putExtra("id",arraysanpham.get(getAdapterPosition()).getID());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //CheckConnect.ShowToast_Short(context,arraysanpham.get(getAdapterPosition()).getTensanpham());
                    context.startActivity(intent);

                }
            });
        }
    }

}
