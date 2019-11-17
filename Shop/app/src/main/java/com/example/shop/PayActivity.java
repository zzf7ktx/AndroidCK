package com.example.shop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.classoop.CartProduct;
import com.example.shop.classoop.ListCartProduct;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {

    Toolbar toolbarPay;
    EditText edtAdress;
    TextView txtTotalMoney;
    Button btnPay;

    ArrayList<CartProduct> arrayList; // Luu bien local

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        toolbarPay = findViewById(R.id.toolbarPay);
        edtAdress = findViewById(R.id.edtAdress);
        txtTotalMoney = findViewById(R.id.txtTotalMoney);
        btnPay = findViewById(R.id.btnPay);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalMoney.setText("Tổng hóa đơn: " + decimalFormat.format(getIntent().getIntExtra("tongbill", 0)) + "VNĐ");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get local
                SessionManager sessionManager = new SessionManager(getApplication());
                arrayList = sessionManager.GetCart().getList();
                int id_temp = sessionManager.GetUser();
                // luu server
                String url = Server.server + "donhang.php?id=" + id_temp + "&diachi=" + edtAdress.getText();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("res donhang:", "success");
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("res donhang:", "fail");
                    }
                });
                requestQueue.add(stringRequest);

                for(int i = 0; i < arrayList.size(); i++){
                    url = Server.server + "chitietdonhang.php";
                    final CartProduct temp = arrayList.get(i);
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i("chitietdonhang :", "success");
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("chitietdonhang :", "fail");
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("masanpham", temp.getIdSanPham()+"");
                            params.put("tensanpham", temp.getTenSanPham());
                            params.put("giasanpham", temp.getGiaSanPham() + "");
                            params.put("soluong", temp.getSoLuong()+"");

                            return params;
                        }
                    };
                    requestQueue.add(stringRequest1);
                }

                // xoa local

                sessionManager.Clear();
                sessionManager.SetUser(id_temp);
            }
        });

        actionToolbar();

    }

    private void actionToolbar() {
        setSupportActionBar(toolbarPay);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarPay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
