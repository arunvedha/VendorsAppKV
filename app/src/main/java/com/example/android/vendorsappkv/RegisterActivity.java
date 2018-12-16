package com.example.android.vendorsappkv;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etPassword,etRenter,etAddress,etMobile,etAadhar;
    Button registerButton;
    String email,password,reenter,aadhar,adress,mobile;
    String TAG = "Register";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        mAuth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etUsername.getText().toString();
                password = etPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RegisterActivity.this, "Registration successful.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String a = e.getLocalizedMessage();
                        Toast.makeText(RegisterActivity.this, ""+a,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void setupUIViews(){
        etUsername = findViewById(R.id.et_username);
        etAadhar = findViewById(R.id.et_aadhar);
        etAddress = findViewById(R.id.et_adress);
        etPassword = findViewById(R.id.et_password);
        etRenter = findViewById(R.id.et_reenter);
        etMobile  = findViewById(R.id.et_mobile);
        registerButton = findViewById(R.id.sigup_reg);
    }
}
