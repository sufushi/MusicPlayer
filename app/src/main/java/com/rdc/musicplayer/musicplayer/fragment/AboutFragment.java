package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class AboutFragment extends BaseFragment {

    public static AboutFragment newInstance(String title) {
        AboutFragment aboutFragment = new AboutFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        aboutFragment.setArguments(bundle);
        return aboutFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_about;
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
