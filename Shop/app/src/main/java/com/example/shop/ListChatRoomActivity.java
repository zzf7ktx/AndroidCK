package com.example.shop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shop.adapter.AdapterChatroom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import com.example.shop.module.Constants;

public class ListChatRoomActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    ListView listView;
    AdapterChatroom adapter;
    ArrayList<Integer> listUser;

    private Socket mSocket;
    Toolbar toolbarChat;

    static Handler UIHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        listView = findViewById(R.id.listRoom);

        toolbarChat = findViewById(R.id.toolbar);
        actionBar();

        progressDialog = new ProgressDialog(ListChatRoomActivity.this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading all users...");
        progressDialog.show();

        // Cai dat adapter cho Listview
        listUser = new ArrayList<>();
        adapter = new AdapterChatroom(getApplication(), listUser);
        listView.setAdapter(adapter);

        // lay du lieu ve, xac nhan so phong de dua vao User
        try {
            mSocket = IO.socket(new Constants().SERVER_URL);
            Log.i("uri", new Constants().SERVER_URL);

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URI", e.toString());
            progressDialog.hide();
        }

        UIHandler = new Handler(Looper.getMainLooper());
        mSocket.connect();

        mSocket.on("client-get-list", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray jsonArray = (JSONArray) args[0];
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Integer temp = jsonObject.getInt("id_user");
                        //Log.i("int: ", "" + temp);
                        listUser.add(temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                UIHandler.post(updateList);
            }
        });
        mSocket.emit("get-list-user-event");
    }

    Runnable updateList = new Runnable() {
        @Override
        public void run() {
            adapter.notifyDataSetChanged();
            progressDialog.cancel();
        }
    };

    private void actionBar()
    {
        setSupportActionBar(toolbarChat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
