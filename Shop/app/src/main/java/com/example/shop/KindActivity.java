package com.example.shop;

import java.util.Comparator;
import  java.util.Collections;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
import com.example.shop.classoop.NameComparator;
import com.example.shop.classoop.PriceComparator;
import com.example.shop.module.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class KindActivity extends AppCompatActivity {
    ArrayList<Product> products;
    KindAdapter kindAdapter;
    ListView lvKind;
    int orderGia = 0;
    int orderTen = 0;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);

        String kind = getIntent().getStringExtra("loai");
        // var
        products = new ArrayList<>();
        kindAdapter = new KindAdapter(getApplication(), products, 1);
        lvKind = findViewById(R.id.lvKind);
        lvKind.setAdapter(kindAdapter);

        Intent intent = getIntent();
        String value = intent.getStringExtra("loai");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(value+"");
        actionToolbar();

        String url = Server.server + "getdata.php?kind=" + value;

        GetProduct(url);
    }
    private void actionToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Intent intent_12 = new Intent(this, LoginActivity.class);
        //Toast.makeText(getApplication(), String.valueOf(orderTen), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.sapxepten:
                orderTen = 1 - orderTen;
                Collections.sort(products, new NameComparator());
                Toast.makeText(getApplication(), String.valueOf(orderTen), Toast.LENGTH_SHORT).show();
                kindAdapter.notifyDataSetChanged();
                break;
            case R.id.sapxepgia:
                orderGia = 1 - orderGia;
                Collections.sort(products, new PriceComparator());
                Toast.makeText(getApplication(), String.valueOf(orderGia), Toast.LENGTH_SHORT).show();
                kindAdapter.notifyDataSetChanged();
                break;

            default:
                Toast.makeText(getApplication(), "Fail", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
