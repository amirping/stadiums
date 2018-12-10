package com.example.pingghost.stadiums;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pingghost.stadiums.model.User;
import com.example.pingghost.stadiums.remote.APIUtils;
import com.example.pingghost.stadiums.remote.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        prefs = this.getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
        passwordE = findViewById(R.id.passwrd);
        usernameE = findViewById(R.id.username);
        logAction = findViewById(R.id.startlog);
        signupGo = findViewById(R.id.gosign);
        userService = APIUtils.getUserService();
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
                    User u = new User("f","f","f",username,password,"f","f","f");
                    authUser(u);

                    //Toast.makeText(view.getContext(),"data : "+username+" - pass : "+password,Toast.LENGTH_LONG).show();
                    // dev mode
                   /* if(username.equals("amir")&&password.equals("amiryes")){
                        prefs.edit().putString("token_app_acc", "isDevMode").apply();
                        if(prefs.contains("token_app_acc")){
                            Intent i = new Intent(view.getContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(AuthActivity.this, "We can't save the token ! ", Toast.LENGTH_LONG).show();
                        }

                    }*/
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
    public void authUser(User u){
        Call<JsonObject> call = userService.logUser(u);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){

                    JsonElement je = response.body();
                    String value = je.getAsJsonObject().get("message").getAsString();
                    Log.e("HELLO OBJECT",value);
                    if(value.equals("auth_success")){
                        String token = je.getAsJsonObject().get("token").getAsString();
                        String _id = je.getAsJsonObject().get("id").getAsString();
                        // auth done
                        // take -> save the token
                        prefs.edit().putString("current_user_id",_id).apply();
                        prefs.edit().putString("token_app_acc", token).apply();
                        if(prefs.contains("token_app_acc")){
                            Toast.makeText(AuthActivity.this, "Successfully Connected ", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(AuthActivity.this, "We can't save the token ! ", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        if(value.equals("auth_fail")){
                            // auth fail
                            Toast.makeText(AuthActivity.this, "Check password or email", Toast.LENGTH_LONG).show();
                        }
                        else{
                            // unknwon error
                            Toast.makeText(AuthActivity.this, "We have troubles , try later", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(AuthActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                Toast.makeText(AuthActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
            }
        });
    }
}
