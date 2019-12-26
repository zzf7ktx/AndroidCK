package com.example.shop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.module.Server;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    Toolbar toolbarAdmin;
    RadioGroup radioGroup;
    EditText edtTen, edtNhanHang, edtGia, edtMota, edtSoLuong, edtGiamGia;
    RadioButton rNon, rQuan, rGiay, rDep, rAo;
    Button btnSummit, btnUpload;
    String tensanpham, nhanhang, gia, mota, soluong, giamgia, danhmuc, image;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        toolbarAdmin = findViewById(R.id.toolbarAdmin);

        Connect();
        actionBar();

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);
            }
        });

        btnSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tensanpham = edtTen.getText().toString();
                nhanhang = edtNhanHang.getText().toString();
                gia = edtGia.getText().toString();
                mota = edtMota.getText().toString();
                soluong = edtSoLuong.getText().toString();
                giamgia = edtGiamGia.getText().toString();
                if(rAo.isChecked()){ danhmuc = "3";}
                else if(rQuan.isChecked()){ danhmuc = "4";}
                else if(rNon.isChecked()){ danhmuc = "2";}
                else if(rGiay.isChecked()){ danhmuc = "1";}
                else if(rDep.isChecked()){ danhmuc = "5";}
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] imgBytes = byteArrayOutputStream.toByteArray();
                image = Base64.encodeToString(imgBytes, Base64.DEFAULT);

                Toast.makeText(AdminActivity.this, "AAAAa", Toast.LENGTH_SHORT).show();
                // check(), check all input
                if(check() == 1){
                    //Log.e("test", "Success");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.server + "postproduct.php",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(AdminActivity.this, response, Toast.LENGTH_SHORT).show();
                                            Toast.makeText(AdminActivity.this, "AAADa", Toast.LENGTH_SHORT).show();
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
                                    params.put("tensanpham", tensanpham);
                                    params.put("nhanhang", nhanhang);
                                    params.put("gia", gia);
                                    params.put("mota", mota);
                                    params.put("soluong", soluong);
                                    params.put("giamgia", giamgia);
                                    params.put("danhmuc", danhmuc);
                                    params.put("image", image);
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

    private void actionBar()
    {

        setSupportActionBar(toolbarAdmin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAdmin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                Toast.makeText(AdminActivity.this, "aaaaB", Toast.LENGTH_SHORT).show();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void Connect() {
        edtTen = findViewById(R.id.u_tensanpham);
        edtNhanHang = findViewById(R.id.u_nhanhang);
        edtGia = findViewById(R.id.u_gia);
        edtMota = findViewById(R.id.u_mota);
        edtSoLuong = findViewById(R.id.u_soluong);
        edtGiamGia = findViewById(R.id.u_giamgia);
        btnUpload = findViewById(R.id.btnUpload);
        btnSummit = findViewById(R.id.btnSummit);
        rAo = findViewById(R.id.r_ao);
        rQuan = findViewById(R.id.r_quan);
        rGiay = findViewById(R.id.r_giay);
        rDep = findViewById(R.id.r_dep);
        rNon = findViewById(R.id.r_non);
    }
    private int check() {
        return 1;
    }


}
