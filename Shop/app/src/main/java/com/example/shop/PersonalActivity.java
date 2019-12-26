package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.classoop.UserInfo;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;
import com.example.shop.personal.MailActivity;
import com.example.shop.personal.NameActivity;
import com.example.shop.personal.PasswordActivity;
import com.example.shop.personal.PhoneActivity;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonalActivity extends AppCompatActivity {

    Toolbar toolbarPersonal;
    ImageButton btnName, btnPhone, btnEmail, btnPassWord;
    TextView tvNamePersonal, tvPhonePersonal, tvMailPersonal;
    ArrayList<UserInfo> userInfos;
    SessionManager sessionManager;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        Connect();
        actionBar();

        userInfos = new ArrayList<>();
        String url = Server.getinfo + "?id=" + id;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                userInfos.add(new UserInfo(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getString("email"),
                                        jsonObject.getString("sdt")
                                ));
                                tvMailPersonal.setText(userInfos.get(0).getEmail());
                                tvNamePersonal.setText(userInfos.get(0).getTen());
                                tvPhonePersonal.setText(userInfos.get(0).getSdt());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "info not found", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);



        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, PhoneActivity.class);
                startActivity(intent);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, MailActivity.class);
                startActivity(intent);
            }
        });
        btnPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });


    }

    private void Connect() {
        toolbarPersonal = findViewById(R.id.toolbarPersonal);
        btnName = findViewById(R.id.btnName);
        btnPhone = findViewById(R.id.btnPhone);
        btnEmail = findViewById(R.id.btnEmail);
        btnPassWord = findViewById(R.id.btnPassWord);
        tvNamePersonal = findViewById(R.id.tvNamePersonal);
        tvPhonePersonal = findViewById(R.id.tvPhonePersonal);
        tvMailPersonal = findViewById(R.id.tvMailPersonal);
        sessionManager = new SessionManager(this);
        id = sessionManager.GetUser();

    }

    private void actionBar()
    {

        setSupportActionBar(toolbarPersonal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPersonal.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
