package com.example.mp_app.L1_Intro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.L2_Main.L2Act_v2;
import com.example.mp_app.R;

public class AuthAct extends AppCompatActivity {
    Button signUpBtn;
    Button signInBtn;
    Button cheatKey;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auth);
        signUpBtn = findViewById(R.id.signUp_btn);
        signInBtn = findViewById(R.id.signIn_btn);
        cheatKey = findViewById(R.id.cheat_btn);
        signUpBtn.setOnClickListener(event -> {
            Intent intent = new Intent(this,SignUpAct.class);
            startActivity(intent);
        });
        signInBtn.setOnClickListener(e -> {
            Intent intent = new Intent(this, SignInAct.class);
            startActivity(intent);
        });
        cheatKey.setOnClickListener(e->{
            Intent intent = new Intent(this,L2Act_v2.class);
            startActivity(intent);
        });
    }
}
