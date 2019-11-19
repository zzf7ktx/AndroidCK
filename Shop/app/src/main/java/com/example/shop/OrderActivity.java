package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.adapter.KindAdapter;
import com.example.shop.adapter.OrderAdapter;
import com.example.shop.classoop.Order;
import com.example.shop.classoop.OrderProduct;
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<OrderProduct> orderProducts;
    OrderAdapter orderAdapter;
    ListView lvOrder;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // var
        orderProducts = new ArrayList<>();
        orderAdapter = new OrderAdapter(getApplication(), orderProducts);
        lvOrder = findViewById(R.id.lvOrder);
        lvOrder.setAdapter(orderAdapter);

        Intent intent = getIntent();
        int value = intent.getIntExtra("idDonHang", 0);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(value+"");

        String url = Server.getOrder + "?id=" + value;

        GetProduct(url);
    }

    private void GetProduct(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                orderProducts.add(new OrderProduct(
                                        jsonObject.getInt("madonhang"),
                                        jsonObject.getInt("masanpham"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getString("hinhanh"),
                                        jsonObject.getInt("soluong")
                                ));
                                orderAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(), "product-kind false", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
