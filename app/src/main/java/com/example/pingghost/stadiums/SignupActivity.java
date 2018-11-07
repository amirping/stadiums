package com.example.pingghost.stadiums;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private String username,email,password;
    private boolean tos_acc;
    private EditText usernameE,passwordE,emailE;
    private CheckBox tos_accE;
    private TextView log_go,tos_read;
    private Button go,cancel;
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
                if(!username.isEmpty()&&username.length()>=6&&!password.isEmpty()&&password.length()>=8&&!email.isEmpty()&&tos_acc){
                    // call for service
                    // send to login
                    // dev mode

                        Intent i = new Intent(view.getContext(), AuthActivity.class);
                        startActivity(i);

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
}
