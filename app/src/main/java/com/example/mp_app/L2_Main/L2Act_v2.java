package com.example.mp_app.L2_Main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mp_app.R;

public class L2Act_v2 extends FragmentActivity {
    private static final int NUM_PAGES = 3;
    private ViewPager2 viewPager2;
    private FragmentPagerAdapter pagerAdapter; //error pos

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_l2_v2);

        viewPager2 = findViewById(R.id.view_pager); //error pos
    }
}