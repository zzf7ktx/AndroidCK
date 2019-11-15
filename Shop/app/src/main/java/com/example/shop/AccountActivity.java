package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class AccountActivity extends AppCompatActivity {

    Toolbar toolbarAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbarAccount =findViewById(R.id.toolbarAccount);

        actionBar();
    }


    private void actionBar()
    {

        setSupportActionBar(toolbarAccount);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarAccount.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
