package com.example.mp_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mp_app.L2_Main.L2Act;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DURATION = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_main);

        //Display splash and move to the other Act.
        setContentView(R.layout.act_splash);

        //Real Code
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, L2Act.class);
            startActivity(intent);
        }, SPLASH_DURATION);
    }
}