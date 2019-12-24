package com.example.shop.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.R;
import com.example.shop.classoop.UserInfo;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    EditText editPhone;
    Button btnChangePhone;
    int id;
    SessionManager sessionManager;
    ArrayList<UserInfo> userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

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
                                editPhone.setText(userInfos.get(0).getSdt());
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

        btnChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editPhone.getText().toString()))
                {
                    Toast.makeText(PhoneActivity.this, "Bạn cần nhập tên", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editPhone(id);
                }
            }
        });

    }

    private void editPhone(final int iD) {
        final String phone = editPhone.getText().toString().trim();
        String url_edit= Server.updatephone;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success"))
                        {
                            Toast.makeText(PhoneActivity.this, "Sửa đổi thành công", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        else
                        {
                            Toast.makeText(PhoneActivity.this, "Không thể thay đổi", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PhoneActivity.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(iD));
                params.put("phone", phone);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Connect() {
        toolbarPhone = findViewById(R.id.toolbarPhone);
        editPhone = findViewById(R.id.editPhone);
        btnChangePhone = findViewById(R.id.btnChangePhone);
        sessionManager = new SessionManager(this);
        id = sessionManager.GetUser();
    }

    private void actionBar() {
        setSupportActionBar(toolbarPhone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
