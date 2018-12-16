package com.example.android.vendorsappkv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,signup;
    TextView forgotPassword;
    String sUsername,sPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    public void setupUIViews(){
        username = findViewById(R.id.user_main);
        password = findViewById(R.id.pass_main);
        login = findViewById(R.id.login_main);
        signup = findViewById(R.id.signup_main);
        forgotPassword = findViewById(R.id.forgot_pass);
    }
}
