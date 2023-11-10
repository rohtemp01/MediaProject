package com.example.mp_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    static boolean splashed = false;
    private final int SPLASH_DURATION = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display splash and move to the other Act.
        setContentView(R.layout.act_splash);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this,AuthAct.class);
            startActivity(intent);
        }, SPLASH_DURATION);

        //setContentView(R.layout.act_main);
    }
}