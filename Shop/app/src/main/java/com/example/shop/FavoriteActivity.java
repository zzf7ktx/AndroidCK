package com.example.shop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shop.adapter.FavoriteAdapter;
import com.example.shop.classoop.Favorite;
import com.example.shop.classoop.FavoriteProduct;
import com.example.shop.classoop.ListFavoriteProduct;
import com.example.shop.module.Server;
import com.example.shop.module.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {


    SessionManager sessionManager;
    ListFavoriteProduct list;
    public static ArrayList<FavoriteProduct> listFavoriteProduct;

    ListView lvFavorite;

    static TextView txtThongBao;

    int id;
    String url;

    FavoriteAdapter favoriteAdapter;
    public static ArrayList<Favorite> listFavorite;

    Toolbar toolbarFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Connect();
        listFavorite = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(getApplication(), listFavorite);
        lvFavorite.setAdapter(favoriteAdapter);

        actionToolBar();

        sessionManager = new SessionManager(this);
        list = sessionManager.GetFavorite();
        listFavoriteProduct = list.getList();

        if(listFavoriteProduct.size()>0)
        {
            txtThongBao.setVisibility(View.INVISIBLE);
        }

        id = sessionManager.GetUser();

        url = Server.server + "getfavorite.php?khachhang=" + id;
        ImportData(url);


    }

    private void ImportData(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject temp = response.getJSONObject(0);
                                //synchronized (listFavorite){
                                listFavorite.add(new Favorite(
                                        temp.getInt("id"),
                                        temp.getString("ten"),
                                        temp.getInt("gia"),
                                        temp.getString("hinhanh")
                                        // So luong san pham add sau
                                ));
                                //}
                                favoriteAdapter.notifyDataSetChanged();
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

                    }
                });
        requestQueue.add(jsonArrayRequest);

        for(int i=0;i<listFavoriteProduct.size(); i++)
        {
            listFavorite.add(new Favorite(
                    listFavoriteProduct.get(i).getIdSanPham(),
                    listFavoriteProduct.get(i).getTenSanPham(),
                    listFavoriteProduct.get(i).getGiaSanPham(),
                    listFavoriteProduct.get(i).getHinhAnhSanPham()
            ));
            favoriteAdapter.notifyDataSetChanged();
        }
    }

    private void actionToolBar() {
        setSupportActionBar(toolbarFavorite);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbarFavorite.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Connect() {
        toolbarFavorite = findViewById(R.id.toolbarFavorite);
        lvFavorite = findViewById(R.id.lvFavorite);
        txtThongBao = findViewById(R.id.tvThongBaoFavorite);
    }

}
