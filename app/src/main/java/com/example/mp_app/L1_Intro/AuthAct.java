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
        signUpBtn.setOnClickListener(v -> {
            // SignUpBtn을 눌렀을 때 SignUpAct로 이동
            Intent intent = new Intent(AuthAct.this, SignUpAct.class);
            startActivity(intent);
        });

        signInBtn.setOnClickListener(v -> {
            // SignInBtn을 눌렀을 때 SignInAct로 이동
            Intent intent = new Intent(AuthAct.this, SignInAct.class);
            startActivity(intent);
        });
    }
}
