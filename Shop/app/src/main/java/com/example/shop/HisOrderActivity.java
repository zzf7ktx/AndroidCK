package com.example.shop;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.adapter.KindAdapter;
import com.example.shop.adapter.OrdersAdapter;
import com.example.shop.classoop.Order;
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HisOrderActivity extends AppCompatActivity {
    ArrayList<Order> orders;
    OrdersAdapter ordersAdapter;
    ListView lvOrders;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_order);
        // var
        orders = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(getApplication(), orders);
        lvOrders = findViewById(R.id.lvOrders);
        lvOrders.setAdapter(ordersAdapter);

        Intent intent = getIntent();
        int value = intent.getIntExtra("id", -1);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(value+"");

        String url = Server.server + "hisorder.php?id=" + value;

        GetOrders(url);
    }

    private void GetOrders(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                orders.add(new Order(
                                        jsonObject.getInt("id"),
                                        jsonObject.getInt("nguoimua"),
                                        jsonObject.getInt("tongtien"),
                                        jsonObject.getString("hinhanh"),
                                        jsonObject.getString("diachi")
                                ));
                                ordersAdapter.notifyDataSetChanged();
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
