package com.example.projeto.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_TABS = 3;
    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return PerfilActivity.newInstance();
            case 1:
                return HomeActivity.newInstance();
            default:
                return ConfigActivity.newInstance();
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//
//        if (position == 0){
//            return "perfil";
//        }
//
//        if (position == 1){
//            return "home";
//        }
//
//        return "config";
//    }

}
