package com.example.mp_app.Tools.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private static final List<Fragment> fragments = new ArrayList<>();
    private static final int NUM_PAGES = fragments.size();
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments.add(new Fragment_0_CameraX());
        fragments.add(new Fragment_1_Chat());
    }
    // 1. onTouch 기능
    // 2. 판단 이후 pager adapter를 콜해서 이동
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
//        switch( position ){
//            case 0:
//                return new FragmentCameraX();
//            case 1:
//                return new FragmentCameraX();
//            case 2:
//                return new FragmentCameraX();
//        }
//        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
