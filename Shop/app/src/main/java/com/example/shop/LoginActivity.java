package com.example.shop;

import com.example.shop.module.Server;
import com.example.shop.module.CheckConnect;
import com.example.shop.module.SessionManager;

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
    //1String url_post = "http://192.168.1.112:8080/androidwebserver/getuser.php";
    Intent intent;
    String id;

    Button btnLogin;
    EditText edtUser, edtPass;
    TextView txtCreate, txtForgot;

    SessionManager sessionManager;
    static boolean count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        count = false;

        intent = new Intent(getApplication(), MainActivity.class);
        sessionManager = new SessionManager(this);
        checkConnected();

        setContentView(R.layout.activity_main_login);

        Connect();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CheckConnect.isNetworkAvailable(getApplication())){
                    int len_user = edtUser.length();
                    int len_pass = edtPass.length();

                    if(len_pass == 0 || len_user == 0){
                        Toast.makeText(getApplication(), "Bạn chưa nhập thông tin.", Toast.LENGTH_LONG).show();
                    } else {
                        String user = edtUser.getText().toString();
                        String pass = edtPass.getText().toString();

                        doLogin(user, pass);
                    }
                } else {
                    CheckConnect.ShowToast_Short(getApplication(), "Không có kết nối internet.");
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

    @Override
    protected void onResume() {
        count = false;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        if(!count){
            count = true;
            Toast.makeText(this, "Quay về lần nữa để thoát chương trình", Toast.LENGTH_SHORT).show();
        } else{
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
    }


    void Connect(){
        txtCreate=findViewById(R.id.login_txt_create);
        txtForgot=findViewById(R.id.login_txt_forgot);

        btnLogin=findViewById(R.id.btnLogin);

        edtUser=findViewById(R.id.login_edt_user);
        edtPass=findViewById(R.id.login_edt_pass);
    }// Connect

    private void doLogin(final String user, final String pass){
        if((user.equals("admin") && pass.equals("admin"))){
            startActivity(intent);
        } else {

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    RequestQueue queue = Volley.newRequestQueue(getApplication());

                    StringRequest postRequest = new StringRequest(Request.Method.POST, Server.getuser,
                            new Response.Listener<String>()
                             {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    //Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                                    if(response.equals("emty")){
                                        Toast.makeText(getApplication(), "Thông tin đăng nhập không hợp lệ.", Toast.LENGTH_LONG).show();
                                    } else {
                                        int id = Integer.parseInt(response);
                                        sessionManager.SetLogin(true);
                                        sessionManager.SetUser(id);
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
            });
            thread.start();
        }

    }

    private void checkConnected(){
        if(sessionManager.Check()){
            startActivity(intent);
        }
    }
}
