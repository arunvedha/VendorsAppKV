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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etPassword,etRenter,etAddress,etMobile,etAadhar,etcity;
    Button registerButton;
    String email,password,reenter,aadhar,adress,mobile,city;
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
                if (validate()){
                    email = etUsername.getText().toString();
                password = etPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    sendUserData();
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
                        Toast.makeText(RegisterActivity.this, "" + a,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
        etcity = findViewById(R.id.et_city);
        registerButton = findViewById(R.id.sigup_reg);
    }

    private boolean validate(){
        email = etUsername.getText().toString();
        password = etPassword.getText().toString();
        reenter = etRenter.getText().toString();
        mobile = etMobile.getText().toString();
        aadhar = etAadhar.getText().toString();
        adress = etAddress.getText().toString();
        city = etcity.getText().toString();

        if (email.isEmpty()|| password.isEmpty()||reenter.isEmpty()||mobile.isEmpty()||adress.isEmpty()||aadhar.isEmpty()||city.isEmpty() ) {
            Toast.makeText(RegisterActivity.this, "please enter all the credentials", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(reenter)) {
            Toast.makeText(RegisterActivity.this, "The passwords dont match,please enter correctly", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private void sendUserData(){
        email = etUsername.getText().toString();
        aadhar = etAadhar.getText().toString();
        adress = etAddress.getText().toString();
        city = etcity.getText().toString().toUpperCase().trim();
        mobile = etMobile.getText().toString();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(city);
        DatabaseReference regUsers = firebaseDatabase.getReference("Registered users");
        UserProfile user = new UserProfile(email,aadhar,adress);
        regUsers.setValue(mobile);
        myRef.child(mobile).setValue(user);

    }


}
