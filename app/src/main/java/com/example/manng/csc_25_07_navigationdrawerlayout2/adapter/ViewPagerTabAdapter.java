package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by manng on 21-Jul-17.
 */

public class ViewPagerTabAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> arrayListFragment = new ArrayList<>();

    public ViewPagerTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return arrayListFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrayListFragment.size();
    }

    public void addFragment (Fragment fragment){
        arrayListFragment.add(fragment);
    }
}
