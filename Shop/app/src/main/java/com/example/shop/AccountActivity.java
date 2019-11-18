package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.adapter.OrdersAdapter;
import com.example.shop.classoop.Navigation;
import com.example.shop.classoop.Order;
import com.example.shop.classoop.Product;
import com.example.shop.classoop.UserInfo;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {
    Context context;
    Toolbar toolbarAccount;
    SessionManager sessionManager;
    TextView tvMail;
    TextView tvName;
    TextView tvLogout;
    TextView tvQLDH;
    ArrayList<UserInfo> userInfos;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        sessionManager = new SessionManager(this);

        toolbarAccount = findViewById(R.id.toolbarAccount);

        tvMail = findViewById(R.id.tvMail);
        tvName = findViewById(R.id.tvAccount);
        id = sessionManager.GetUser();
        tvLogout = findViewById(R.id.tvLogout);
        tvQLDH = findViewById(R.id.tvQLDH);
        userInfos = new ArrayList<>();
        String url = Server.getinfo + "?id=" + id;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                userInfos.add (new UserInfo(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getString("email")
                                ));
                                tvMail.setText(userInfos.get(0).getEmail());
                                tvName.setText(userInfos.get(0).getTen());
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
        actionBar();
      //  GetInfo();
        tvQLDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), HisOrderActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLogout.setSelected(true);
                sessionManager.SetLogin(false);
                sessionManager.SetUser(-1);
                sessionManager.Clear();
                finish();
            }
        });
    }

    private void actionBar()
    {

        setSupportActionBar(toolbarAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAccount.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void GetInfo(){


    }
}
