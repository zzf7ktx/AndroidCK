package com.example.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.classoop.CartProduct;
import com.example.shop.classoop.ListCartProduct;
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    int values;
    Toolbar toolbarDetails;
    Product myProduct;

    ListCartProduct listCart;

    int soluong = 1;

    ImageView imageView;
    Button btnMinusleft, btnValues, btnMinusright, btnAddCart;
    TextView txttensanpham, txtgiasanpham, txtmotasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // ket noi
        Connect();

        Intent intent = getIntent();
        values = intent.getIntExtra("id", 0);

        toolbarDetails =findViewById(R.id.toolbarDetails);

        actionToolbar();

        Log.i("id send", values + "");
        // resquest
        if(values == 0){
            Toast.makeText(this, "loi id product", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getDetail + "?id=" + values, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i = 0; i < response.length(); i++){
                                try {
                                    JSONObject temp = response.getJSONObject(i);
                                    myProduct = new Product(0, null, null, null, null, 0);
                                    myProduct.setID(temp.getInt("id"));
                                    myProduct.setTensanpham(temp.getString("ten"));
                                    myProduct.setGiasanpham(temp.getInt("gia"));
                                    myProduct.setHinhanhsanpham(temp.getString("hinhanh"));
                                    myProduct.setMotasanpham(temp.getString("mota"));
                                    myProduct.setIDSanpham(temp.getInt("idLoai"));

                                    addToWidget();
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.i("test leng res", response.length() + "");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("test res:", "fails id:" + values);
                        }
                    }
            );
            requestQueue.add(jsonArrayRequest);
        }

        //Xu li button
        btnValues.setEnabled(false);

        btnMinusleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluong <= 1){
                    Toast.makeText(DetailsActivity.this, "Đã đạt số lượng tối thiểu", Toast.LENGTH_SHORT).show();
                } else{
                    soluong--;
                    btnValues.setText(soluong+"");
                }
            }
        });

        btnMinusright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluong >= 50){
                    Toast.makeText(DetailsActivity.this, "Đã đạt số lượng tối đa", Toast.LENGTH_SHORT).show();
                } else {
                    soluong++;
                    btnValues.setText(soluong+"");
                }
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManager sessionManager = new SessionManager(getApplication());
                listCart = sessionManager.GetCart();

                // check san pham trong gio hang local
                int idSP = myProduct.getID();
                int size = listCart.getList().size();
                boolean kt = false;

                for(int i = 0; i < size; i++){
                    if(idSP == listCart.getList().get(i).getIdSanPham()){
                        listCart.getList().get(i).setSoLuong(listCart.getList().get(i).getSoLuong());
                        kt = true;
                    }
                }

                if(!kt){
                    listCart.getList().add(new CartProduct(sessionManager.GetUser(), idSP, Integer.parseInt((String) btnValues.getText())));
                }

                sessionManager.SetCart(listCart);

                Toast.makeText(DetailsActivity.this, "Đã thêm vào giỏ hàng thành công.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarDetails);
        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.menuAccount:
                Intent intent_1 = new Intent(this, AccountActivity.class);
                startActivity(intent_1);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Connect(){
        imageView = findViewById(R.id.detail_imgView);

        btnMinusleft =findViewById(R.id.buttonminusleft);
        btnMinusright =findViewById(R.id.buttonminusright);
        btnValues =findViewById(R.id.buttonvalue);
        btnAddCart =findViewById(R.id.buttondatmua);

        txtgiasanpham = findViewById(R.id.textviewgiasanpham);
        txttensanpham = findViewById(R.id.textviewtenchitietsanpham);
        txtmotasan = findViewById(R.id.textviewchitietsanpham);
    }

    private void addToWidget(){
        Picasso.get().load(myProduct.getHinhanhsanpham()).into(imageView);
        txttensanpham.setText(myProduct.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        txtgiasanpham.setText(decimalFormat.format(myProduct.getGiasanpham())+" VNĐ");
        txtmotasan.setText(myProduct.getMotasanpham());
    }
}


