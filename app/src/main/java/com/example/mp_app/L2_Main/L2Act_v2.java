package com.example.mp_app.L2_Main;

import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mp_app.R;
import com.example.mp_app.Tools.ViewPager.Fragment_0_CameraX_v2;
import com.example.mp_app.Tools.ViewPager.Slider_PagerAdapter;

public class L2Act_v2 extends FragmentActivity {
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter; //error pos
    Button btn;
    Fragment_0_CameraX_v2 fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_l2_v2);

        viewPager2 = findViewById(R.id.view_pager); //error pos
        pagerAdapter = new Slider_PagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
        //fragment = (Fragment_0_CameraX_v2) getSupportFragmentManager().findFragmentById(R.id.frag_camerax);

//        btn = findViewById(R.id.button);
//        btn.setOnClickListener(e->{
//            System.out.println(viewPager2.getCurrentItem());
//            viewPager2.setCurrentItem(1);
//            fragment.work();
//        });
    }
}