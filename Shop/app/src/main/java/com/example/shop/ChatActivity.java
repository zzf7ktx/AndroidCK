package com.example.shop;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.shop.R;
import com.example.shop.adapter.AdapterMessage;
import com.example.shop.module.Constants;
import com.example.shop.classoop.Message;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    ListView listView;
    EditText edtChat;
    TextView txtId;
    ImageButton imgSend;

    AdapterMessage adapter;
    ArrayList<Message> list;

    Toolbar toolbarChat;

    int id, idSendTo;
    Integer idObj;

    Socket mSocket;
    static Handler UIHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);

        toolbarChat = findViewById(R.id.toolbar);
        actionBar();

        progressDialog = new ProgressDialog(ChatActivity.this, ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading all messages...");
        progressDialog.show();

        try {
            mSocket = IO.socket(new Constants().SERVER_URL);
            Log.i("uri", new Constants().SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e("URI", e.toString());
            progressDialog.hide();
        }

        mSocket.connect();
        UIHandler = new Handler(Looper.getMainLooper());


        // Get id, idsendto
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        idSendTo = intent.getIntExtra("idSendTo", 0);

        if (id == 0 || idSendTo == 0) {
            Log.e("Get id:", "Loi khong lay duoc du lieu tu Intent");
            return;
        }

        // Connect widgets
        ConnectWidgets();

        // Set id
        if (id != 1) {
            idObj = id;
        } else {
            idObj = idSendTo;
        }

        // Set listview
        list = new ArrayList<>();
        adapter = new AdapterMessage(list, getApplication(), idObj);
        listView.setAdapter(adapter);

        // Fill data to listview
        GetMess(idObj);

        txtId.setText("Room chat: " + idObj.toString());

        // Set button event;
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess = edtChat.getText().toString();
                if (mess.equals("")) {
                    Toast.makeText(ChatActivity.this, "Bạn chưa nhập tin nhắn", Toast.LENGTH_SHORT).show();
                } else {
                    SendMess(idObj, mess, (id != 1));
                    edtChat.setText("");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSocket.disconnect();
        mSocket.close();
    }

    private void ConnectWidgets() {
        listView = findViewById(R.id.mess_lv_messbox);
        edtChat = findViewById(R.id.mess_edt_chat);
        txtId = findViewById(R.id.mess_txt_user);
        imgSend = findViewById(R.id.mess_img_send);
    }

    private void GetMess(Integer _id) {
        mSocket.emit("join-room", _id);
        mSocket.emit("get-message-event");

        mSocket.on("client-get-mess", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray jsonArray = (JSONArray) args[0];
                int len = jsonArray.length();
                Log.i("len", len + "");
                for (int i = 0; i < len; i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String date = jsonObject.getString("time");

                        DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
                        DateTime time = parser2.parseDateTime(date.substring(0, date.length() - 5) + "Z");

                        String detail = jsonObject.getString("detail");
                        Boolean send = jsonObject.getBoolean("send");

                        Log.i("mess:", time.toString() + " || " + detail + " || " + send.toString());

                        Message temp = new Message(
                                time,
                                detail,
                                send
                        );


                        list.add(temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                UIHandler.post(updateList);
            }
        });

        mSocket.on("client-get-one-mess", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                try {
                    JSONObject jsonObject = (JSONObject) args[0];
                    String date = jsonObject.getString("time");

                    DateTime time = new DateTime(date);

                    String detail = jsonObject.getString("detail");
                    Boolean send = jsonObject.getBoolean("send");

                    Message temp = new Message(
                            time,
                            detail,
                            send
                    );

                    list.add(temp);
                    UIHandler.post(updateList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Runnable updateList = new Runnable() {
        @Override
        public void run() {
            adapter.notifyDataSetChanged();
            listView.setSelection(adapter.getCount() - 1);
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


    private void SendMess(Integer _id, String _mess, Boolean _send) {
//        mSocket.emit("join-room", _id);
        mSocket.emit("send-message-event", _mess, _send);
    }
}
