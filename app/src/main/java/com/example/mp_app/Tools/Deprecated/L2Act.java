package com.example.mp_app.Tools.Deprecated;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mp_app.R;
import com.example.mp_app.Tools.Deprecated.PagerAdapter;

public class L2Act extends AppCompatActivity {
    Button btn;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_l2);
        viewPager = findViewById(R.id.view_pager);
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