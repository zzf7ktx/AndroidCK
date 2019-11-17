package com.example.shop.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shop.classoop.CartProduct;
import com.example.shop.classoop.ListCartProduct;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SessionManager {
    private static String TAG = SessionManager.class.getName();
    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    private static int PRE_MODE = 1;
    private static final String NAME = "android_demo";
    private static final String KEY_LOGIN = "islogin";
    private static final String USER = "user";

    public SessionManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void SetUser(int user){
        editor.putInt(USER, user);
        editor.commit();
    }

    public int GetUser(){
        return preferences.getInt(USER, 0);
    }

    public void SetLogin(boolean isLogin){
        editor.putBoolean(KEY_LOGIN, isLogin);
        editor.commit();
    }
    public boolean Check(){
        return preferences.getBoolean(KEY_LOGIN, false);
    }

    public void SetCart(ListCartProduct list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("MyCart", json);
        editor.commit();
    }

    public ListCartProduct GetCart(){
        String json = preferences.getString("MyCart", "");
        if(json.equals("")){
            ListCartProduct list = new ListCartProduct(new ArrayList<CartProduct>());
            SetCart(list);
        }
        json = preferences.getString("MyCart", "");
        Gson gson = new Gson();
        return gson.fromJson(json, ListCartProduct.class);
    }
}
