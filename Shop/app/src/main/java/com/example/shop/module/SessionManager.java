package com.example.shop.module;

import android.content.Context;
import android.content.SharedPreferences;

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
}
