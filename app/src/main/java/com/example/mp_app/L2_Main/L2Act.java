package com.example.mp_app.L2_Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.example.mp_app.R;
import com.example.mp_app.Tools.ViewPager.PagerAdapter;

public class L2Act extends AppCompatActivity {
    Button btn;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_l2);
        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(event->{
            System.out.println("From " + viewPager.getCurrentItem());
            viewPager.setCurrentItem(1);
            System.out.println("To " + viewPager.getCurrentItem());
        });

    }
}