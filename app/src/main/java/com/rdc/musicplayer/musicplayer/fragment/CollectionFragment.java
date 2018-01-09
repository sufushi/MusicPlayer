package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class CollectionFragment extends BaseFragment {

    public static CollectionFragment newInstance(String title) {
        CollectionFragment collectionFragment = new CollectionFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        collectionFragment.setArguments(bundle);
        return collectionFragment;
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_collection;
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
