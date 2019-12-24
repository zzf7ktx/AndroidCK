package com.example.shop.personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.R;
import com.example.shop.RegisterActivity;
import com.example.shop.classoop.UserInfo;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.Attributes;

public class NameActivity extends AppCompatActivity {

    Toolbar toolbarName;
    EditText editName;
    Button btnChangeName;
    SessionManager sessionManager;
    int id;
    ArrayList<UserInfo> userInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

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
                                editName.setText(userInfos.get(0).getTen());
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

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editName.getText().toString()))
                {
                    Toast.makeText(NameActivity.this, "Bạn cần nhập tên", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editName(id);
                }
            }
        });

    }

    private void editName(final int iD) {
        final String name = editName.getText().toString().trim();
        String url_edit= Server.updatename;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_edit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Success"))
                        {
                            Toast.makeText(NameActivity.this, "Sửa đổi thành công", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                        else
                        {
                            Toast.makeText(NameActivity.this, "Không thể thay đổi", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NameActivity.this, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(iD));
                params.put("ten", name);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Connect() {
        toolbarName = findViewById(R.id.toolbarName);
        editName = findViewById(R.id.editName);
        btnChangeName = findViewById(R.id.btnChangeName);
        sessionManager = new SessionManager(this);
        id = sessionManager.GetUser();
    }

    private void actionBar() {
        setSupportActionBar(toolbarName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarName.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
