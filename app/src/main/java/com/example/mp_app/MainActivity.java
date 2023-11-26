package com.example.mp_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mp_app.L2_Main.L2Act_v2;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DURATION = 2000;
    public static int myFID =1;
            //sharedPref.getInt("FID", -1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.act_main);
        //Display splash
        setContentView(R.layout.act_splash);

        //스플래시 표시하고 다음 액티비티로 가기
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, L2Act_v2.class);
            startActivity(intent);
        }, SPLASH_DURATION);
    }
}