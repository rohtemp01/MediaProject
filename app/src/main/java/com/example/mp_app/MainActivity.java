package com.example.mp_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mp_app.L1_Intro.AuthAct;
import com.example.mp_app.L2_Main.L2Act;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DURATION = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.act_main);

        //Display splash
        setContentView(R.layout.act_splash);

        //스플래시 표시하고 다음 액티비티로 가기
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, AuthAct.class);
            startActivity(intent);
        }, SPLASH_DURATION);
    }
}