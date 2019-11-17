package com.example.shop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.adapter.CartAdapter;
import com.example.shop.classoop.Cart;
import com.example.shop.classoop.CartProduct;
import com.example.shop.classoop.ListCartProduct;
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    SessionManager sessionManager;
    ListCartProduct list;
    public static ArrayList<CartProduct> listCartProduct;

    ListView lvCart;
    TextView txtTotalBill, txtThongBao;
    Button btnPayNext;
    Button btnPay;

    CartAdapter cartAdapter;
    public static ArrayList<Cart> listCart;

    Toolbar toolbarCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Connect();
        listCart = new ArrayList<>();
        cartAdapter = new CartAdapter(getApplication(), listCart);
        lvCart.setAdapter(cartAdapter);


        actionToolbar();

        // Do du lieu
        sessionManager = new SessionManager(this);
        list = sessionManager.GetCart();
        listCartProduct = list.getList();

        Toast.makeText(getApplication(), listCartProduct.size() + "", Toast.LENGTH_SHORT).show();

        if (listCartProduct.size() > 0) {
            txtThongBao.setVisibility(View.INVISIBLE);
        }

        ImportData();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        btnPayNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void Connect() {
        btnPay = findViewById(R.id.btnBuy);
        btnPayNext = findViewById(R.id.btnMuaHang);
        txtTotalBill = findViewById(R.id.txtTotalBill);
        lvCart = findViewById(R.id.lvCart);
        toolbarCart = findViewById(R.id.toolbarCart);
        txtThongBao = findViewById(R.id.tvThongBao);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarCart);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ImportData() {
        // Cach 1: dung SQL truy van:
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        for(int i = 0; i < listCartProduct.size(); i = i + 1){
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getDetail + "?id=" + listCartProduct.get(i).getIdSanPham(), null,
//                    new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            try {
//                                JSONObject temp = response.getJSONObject(0);
//                                //synchronized (listCart){
//                                    listCart.add(new Cart(
//                                            temp.getInt("id"),
//                                            temp.getString("ten"),
//                                            temp.getInt("gia"),
//                                            temp.getString("hinhanh"),
//                                            0
//                                            // So luong san pham add sau
//                                    ));
//                                //}
//                                cartAdapter.notifyDataSetChanged();
//                            }
//                            catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
//            requestQueue.add(jsonArrayRequest);
//      }
//  }
        // Cach 2:
        for(int i = 0; i < listCartProduct.size(); i++){
            listCart.add(new Cart(
                    listCartProduct.get(i).getIdSanPham(),
                    listCartProduct.get(i).getTenSanPham(),
                    listCartProduct.get(i).getGiaSanPham(),
                    listCartProduct.get(i).getHinhAnhSanPham(),
                    listCartProduct.get(i).getSoLuong()
            ));
            cartAdapter.notifyDataSetChanged();
            Toast.makeText(this, listCart.get(i).getIdsp() + "", Toast.LENGTH_SHORT).show();
        }

    }
}
