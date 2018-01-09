package com.rdc.musicplayer.musicplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import java.util.List;

public class MusicVpAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList;
    private String[] mTitles;

    public MusicVpAdapter(FragmentManager fm, List<BaseFragment> fragmentList, String[] titles) {
        super(fm);
        mFragmentList = fragmentList;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
