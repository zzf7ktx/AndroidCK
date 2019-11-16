package com.example.shop;

import com.example.shop.module.Server;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUser, edtEmail, edtPass, edtRePass, edtPhone;
    Button btnRegister;

    String user, email, pass, repass, phone;
    //String url_post = "http://192.168.1.112:8080/androidwebserver/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Connect();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edtUser.getText().toString();
                email = edtEmail.getText().toString();
                pass = edtPass.getText().toString();
                repass = edtRePass.getText().toString();
                phone = edtPhone.getText().toString();

                // check(), check all input
                if(check(user, email, pass, repass, phone)){
                    //Log.e("test", "Success");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.register,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                            if(response.equals("Success")){
                                                onBackPressed();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                @Override
                                protected Map<String, String> getParams(){
                                    Map<String, String>  params = new HashMap<String, String>();
                                    params.put("user", user);
                                    params.put("pass", pass);
                                    params.put("email", email);
                                    params.put("phone", phone);

                                    return params;
                                }
                            };
                            requestQueue.add(stringRequest);
                        }
                    });
                    thread.start();
                }
            }
        });
    }

    private void Connect() {
        edtUser = findViewById(R.id.register_edtUser);
        edtEmail = findViewById(R.id.register_edtEmail);
        edtPass = findViewById(R.id.register_edtPass);
        edtRePass = findViewById(R.id.register_edtRePass);
        edtPhone = findViewById(R.id.register_edtPhone);
        btnRegister = findViewById(R.id.register_btnRegister);
    }

    private boolean check(String _user, String _email, String _pass, String _repass, String _phone){
        //pass
        if(_pass.length() < 6){
            Toast.makeText(getApplication(), "Mật khẩu quá ngắn.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //pass and repass
        if(!_pass.equals(_repass)){
            Toast.makeText(getApplication(), "Xác nhận mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!_email.contains("@")){
            Toast.makeText(getApplication(), "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(_phone.length() != 10 || !isNumeric(_phone)){
            Toast.makeText(getApplication(), "Số điện thoại không đúng định dạng.", Toast.LENGTH_SHORT).show();
            return false;
        }

        // return
        return true;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
