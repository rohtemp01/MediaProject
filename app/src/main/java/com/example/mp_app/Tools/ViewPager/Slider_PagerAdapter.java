package com.example.mp_app.Tools.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class Slider_PagerAdapter extends FragmentStateAdapter {
    private static final List<Fragment> fragments = new ArrayList<>();
    private static final int NUM_PAGES = 2;
    public Slider_PagerAdapter(FragmentActivity fa) {
        super(fa);
        System.out.println("~~~~~Slider_PagerAdapter(FragmentActivity fa) ");
        fragments.add(new Fragment_0_CameraX_v2());
        fragments.add(new Fragment_1_Chat_v2());
        fragments.add(new Fragment_2_Dictionary());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}