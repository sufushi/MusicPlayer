package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class ShareFragment extends BaseFragment {

    public static ShareFragment newInstance(String title) {
        ShareFragment shareFragment = new ShareFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        shareFragment.setArguments(bundle);
        return shareFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_share;
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
