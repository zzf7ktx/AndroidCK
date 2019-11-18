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
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KindActivity extends AppCompatActivity {
    ArrayList<Product> products;
    KindAdapter kindAdapter;
    ListView lvKind;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);

        String kind = getIntent().getStringExtra("loai");
        // var
        products = new ArrayList<>();
        kindAdapter = new KindAdapter(getApplication(), products);
        lvKind = findViewById(R.id.lvKind);
        lvKind.setAdapter(kindAdapter);

        Intent intent = getIntent();
        String value = intent.getStringExtra("loai");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(value+"");

        String url = Server.server + "getdata.php?kind=" + value;

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
                                products.add(new Product(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getString("hinhanh"),
                                        jsonObject.getString("mota"),
                                        jsonObject.getInt("idLoai")
                                ));
                                kindAdapter.notifyDataSetChanged();
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
