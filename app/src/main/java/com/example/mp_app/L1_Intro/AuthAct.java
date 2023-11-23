package com.example.mp_app.L1_Intro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.R;

public class AuthAct extends AppCompatActivity {
    Button signUpBtn;
    Button signInBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auth);
        signUpBtn = findViewById(R.id.signUp_btn);
        signInBtn = findViewById(R.id.signIn_btn);
        signUpBtn.setOnClickListener(event -> {
            Intent intent = new Intent();
            startActivity(intent);
        });
        signInBtn.setOnClickListener(e -> {
            Intent intent = new Intent();
            startActivity(intent);
        });
    }
}
