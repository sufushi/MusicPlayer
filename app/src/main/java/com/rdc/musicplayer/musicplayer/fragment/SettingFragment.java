package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance(String title) {
        SettingFragment settingFragment = new SettingFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        settingFragment.setArguments(bundle);
        return settingFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_setting;
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
