package com.example.shop;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.shop.adapter.FlashSaleAdapter;
import com.example.shop.adapter.NavigationAdapter;
import com.example.shop.adapter.NewProductAdapter;
import com.example.shop.classoop.Navigation;
import com.example.shop.classoop.Product;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;
import org.json.*;

import java.util.ArrayList;

import static com.example.shop.R.menu.menu;


public class MainActivity extends AppCompatActivity {
    // Check getSharePreference
    SessionManager sessionManager;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewFlipper viewFlipper1;
    //NavigationView navigationView;


    // Flashsale product
    static ArrayList<Product> arrayListSale;
    RecyclerView recyclerView;

    // Product adapter
    ArrayList<Product> arrayListProduct;
    RecyclerView recyclerView2;

    // Navigation
    ArrayList<Navigation> listNavigation;
    ListView listView;


    // Adapter
    FlashSaleAdapter flashSaleAdapter;
    NewProductAdapter productAdapter;
    NavigationAdapter navigationAdapter;


    Button btn;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sessionManager = new SessionManager(this);
        sessionManager.Clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);
        drawerLayout = findViewById(R.id.drawer);


        toolbar = findViewById(R.id.toolbar);

        viewFlipper1 = findViewById(R.id.detail_viewFlipper);

        myStartViewFlipper();
        //navigationView =findViewById(R.id.navigation_view);
        listView = findViewById(R.id.listViewManHinhChinh);
        actionBar();


        // RecyclerView flashsale
        arrayListSale = new ArrayList<>();
        recyclerView = this.findViewById(R.id.recyclerViewHotItems);
        flashSaleAdapter = new FlashSaleAdapter(getApplication(), arrayListSale);
        GetProductFlashSale();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(flashSaleAdapter);
        // End flashsale

        // RecyclerView product
        arrayListProduct = new ArrayList<>();
        recyclerView2 = this.findViewById(R.id.recyclerView);
        productAdapter = new NewProductAdapter(getApplication(), arrayListProduct);
        GetProduct();
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView2.setAdapter(productAdapter);
        // End products

        // Listview navigation
        listNavigation = new ArrayList<>();
        //listView = this.findViewById(R.id.recyclerView);
        navigationAdapter = new NavigationAdapter(listNavigation, getApplication());
        GetNav();
        //recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        listView.setAdapter(navigationAdapter);
        // End navigation
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.hamburger_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent_12 = new Intent(this, LoginActivity.class);
        switch (item.getItemId()) {
            case R.id.menugiohang:
                if (sessionManager.GetUser() != 0) {
                    Intent intent = new Intent(this, CartActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(intent_12);
                }
                break;
            case R.id.menuAccount:
                if (sessionManager.GetUser() != 0) {
                    Intent intent_1 = new Intent(this, AccountActivity.class);
                    startActivity(intent_1);
                } else {
                    startActivity(intent_12);
                }

        }
        return super.onOptionsItemSelected(item);
    }

    // Do san pham ra flashsale
    private void GetProductFlashSale() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getSaleProduct, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                arrayListSale.add(new Product(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getString("hinhanh"),
                                        jsonObject.getString("mota"),
                                        jsonObject.getInt("idLoai")
                                ));
                                flashSaleAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("len", arrayListSale.size() + "");
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "flashsale false", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


    // Do san pham ra product
    private void GetProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getProduct, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                arrayListProduct.add(new Product(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getInt("gia"),
                                        jsonObject.getString("hinhanh"),
                                        jsonObject.getString("mota"),
                                        jsonObject.getInt("idLoai")
                                ));
                                productAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("len", arrayListProduct.size() + "");
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "false", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // Nav, do du lieu
    private void GetNav() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.getKind, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listNavigation.add(new Navigation(
                                        jsonObject.getInt("id"),
                                        jsonObject.getString("ten"),
                                        jsonObject.getString("hinhanh")
                                ));
                                navigationAdapter.notifyDataSetChanged();
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
                        Toast.makeText(MainActivity.this, "nav false", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // viewFlipper
    private void myStartViewFlipper() {

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewFlipper1.setAutoStart(true);
        viewFlipper1.setFlipInterval(5000);
        viewFlipper1.startFlipping();


        // set the animation type to ViewFlipper
        viewFlipper1.setInAnimation(in);
        viewFlipper1.setOutAnimation(out);
    }
}
