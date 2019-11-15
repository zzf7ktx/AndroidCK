package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String url_post = "http://192.168.1.112:8080/androidwebserver/getuser.php";

    String vaitro;

    Button btnLogin;
    EditText edtUser, edtPass;
    TextView txtCreate, txtForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Connect();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len_user = edtUser.length();
                int len_pass = edtPass.length();

                if(len_pass == 0 || len_user == 0){
                    Toast.makeText(getApplication(), "Bạn chưa nhập thông tin.", Toast.LENGTH_LONG).show();
                } else {
                    String user = edtUser.getText().toString();
                    String pass = edtPass.getText().toString();

                    doLogin(user, pass);
                }
            }
        });


        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    } // onCreate

    void Connect(){
        txtCreate=findViewById(R.id.login_txt_create);
        txtForgot=findViewById(R.id.login_txt_forgot);

        btnLogin=findViewById(R.id.btnLogin);

        edtUser=findViewById(R.id.login_edt_user);
        edtPass=findViewById(R.id.login_edt_pass);
    }// Connect

    private void doLogin(final String user, final String pass){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url_post,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        // Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                        if(!response.equals("1") && !response.equals("2")){
                            Toast.makeText(getApplication(), "Thông tin đăng nhập không hợp lệ.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplication(), response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("vaitro", vaitro);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user", user);
                params.put("pass", pass);

                return params;
            }
        };
        queue.add(postRequest);
    }
}
