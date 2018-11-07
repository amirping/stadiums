package com.example.pingghost.stadiums;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String token = "";
    public SharedPreferences prefs;
    private final String TAG ="MAIN-ACT ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // start check for token
        prefs =  this.getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
        Boolean isexiste = prefs.contains("token_app_acc");
        token = prefs.getString("token_app_acc","00_no_token_found_00");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        if(isexiste && !token.equals("00_no_token_found_00")){
            Log.d(TAG, "TOKEN IS :"+token);
            // check if still up

            // if yes go to home

            // dev mode
            if (token.equals("isDevMode")){
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
            }
        }
        else{
            // go to login
            Intent i = new Intent(this, AuthActivity.class);
            startActivity(i);
        }
    }
}
