package com.example.pingghost.stadiums;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pingghost.stadiums.model.User;
import com.example.pingghost.stadiums.remote.APIUtils;
import com.example.pingghost.stadiums.remote.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;
import org.json.JSONTokener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private String username,email,password,phone,lastname;
    private boolean tos_acc;
    private EditText usernameE,passwordE,emailE,lastnameE,phoneE;
    private CheckBox tos_accE;
    private TextView log_go,tos_read;
    private Button go,cancel;
    UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameE = findViewById(R.id.username);
        passwordE = findViewById(R.id.passwd);
        emailE = findViewById(R.id.email);
        tos_accE = findViewById(R.id.acc_tos);
        log_go = findViewById(R.id.loggo);
        tos_read = findViewById(R.id.tosread);
        go = findViewById(R.id.go);
        cancel = findViewById(R.id.cancel);
        phoneE = findViewById(R.id.phone);
        lastnameE = findViewById(R.id.lastname);
        userService = APIUtils.getUserService();
        log_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AuthActivity.class);
                startActivity(i);
            }
        });
        tos_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameE.getText().toString();
                password=passwordE.getText().toString();
                email=emailE.getText().toString();
                tos_acc=tos_accE.isChecked();
                phone = phoneE.getText().toString();
                lastname = lastnameE.getText().toString();
                if(!username.isEmpty()&&username.length()>=1&&!password.isEmpty()&&password.length()>=6&&!email.isEmpty()&&tos_acc&&!phone.isEmpty()&&phone.length()>=8){
                    // call for service
                    User u  = new User("",username,lastname,email,password,phone,"","");
                    registerUser(u);
                    // send to login
                    // dev mode
                       /* Intent i = new Intent(view.getContext(), AuthActivity.class);
                        startActivity(i);*/
                }
                else{
                    Toast.makeText(SignupActivity.this, "Please make sure that you provide valide data", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = "";
                password="";
                email="";
                tos_acc=false;
                usernameE.setText("");
                passwordE.setText("");
                emailE.setText("");
                tos_accE.setChecked(false);
            }
        });
    }
    public void registerUser(User u){
        Call<JsonObject> call = userService.registerUser(u);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){

                    JsonElement je = response.body();
                    String value = je.getAsJsonObject().get("message").getAsString();
                        Log.e("HELLO OBJECT",value);
                    if(value.equals("user_created")){
                        // creation done
                        Toast.makeText(SignupActivity.this, "Successfully Created ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), AuthActivity.class);
                        startActivity(i);
                    }
                    else{
                        if(value.equals("email_exists")){
                            // email already used
                            Toast.makeText(SignupActivity.this, "This Email Used by some one else", Toast.LENGTH_LONG).show();
                        }
                        else{
                            // unknwon error
                            Toast.makeText(SignupActivity.this, "We have troubles , try later", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(SignupActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
                Toast.makeText(SignupActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
            }
        });
    }
}
