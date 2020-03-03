package com.example.augshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {
    private Button CreateAccountButton;
    private EditText InputEmail, InputPassword;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        CreateAccountButton = (Button) findViewById(R.id.register);
        InputEmail = (EditText) findViewById(R.id.reg_email);
        InputPassword = (EditText) findViewById(R.id.reg_password);
        CreateAccountButton.setOnClickListener(v -> CreateAccount());
    }

    private void CreateAccount() {
        String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Write your email id...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Write your password...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Creating Account.....", Toast.LENGTH_SHORT).show();

            Validate(email,password);
        }
    }

    private void Validate(String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(registerActivity.this, HomeActivity.class));
                    finish();
                }
            }
        });
    }


}
