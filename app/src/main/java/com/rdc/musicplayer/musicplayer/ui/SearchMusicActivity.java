package com.rdc.musicplayer.musicplayer.ui;

import android.content.Intent;
import android.os.Bundle;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseActivity;
import com.rdc.musicplayer.musicplayer.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SearchMusicActivity extends BaseActivity {

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_search_music;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
        mSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Intent intent = new Intent(SearchMusicActivity.this, OnLineMusicActivity.class);
                intent.putExtra("keyword", string);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
