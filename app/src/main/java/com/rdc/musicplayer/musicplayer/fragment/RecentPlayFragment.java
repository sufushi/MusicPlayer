package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class RecentPlayFragment extends BaseFragment {

    public static RecentPlayFragment newInstance(String title) {
        RecentPlayFragment recentPlayFragment = new RecentPlayFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        recentPlayFragment.setArguments(bundle);
        return recentPlayFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_recent_play;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
    }

    private void setParams(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
