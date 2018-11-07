package com.example.pingghost.stadiums;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AuthActivity extends AccountAuthenticatorActivity {
    public static final String ARG_ACCOUNT_TYPE = "accountType";
    public static final String ARG_AUTH_TOKEN_TYPE = "authTokenType";
    public static final String ARG_IS_ADDING_NEW_ACCOUNT = "isAddingNewAccount";
    public static final String PARAM_USER_PASSWORD = "password";
    private String password,username ;
    private EditText passwordE,usernameE;
    private Button logAction;
    private TextView signupGo;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        prefs = this.getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
        passwordE = findViewById(R.id.passwrd);
        usernameE = findViewById(R.id.username);
        logAction = findViewById(R.id.startlog);
        signupGo = findViewById(R.id.gosign);
        logAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = passwordE.getText().toString();
                username = usernameE.getText().toString();
                if(password.isEmpty() || username.isEmpty() || password.length()<6 || username.length()<2){
                    // show error
                    Toast.makeText(view.getContext(),"Please provide valid data",Toast.LENGTH_LONG).show();
                }else{
                    // call service for login -> save token -> go ahead
                    Toast.makeText(view.getContext(),"data : "+username+" - pass : "+password,Toast.LENGTH_LONG).show();

                    // dev mode
                    if(username.equals("amir")&&password.equals("amiryes")){
                        prefs.edit().putString("token_app_acc", "isDevMode").apply();
                        if(prefs.contains("token_app_acc")){
                            Intent i = new Intent(view.getContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(AuthActivity.this, "We can't save the token ! ", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });
        signupGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SignupActivity.class);
                startActivity(i);
            }
        });
    }
}
