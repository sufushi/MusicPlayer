package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.LocalSingerRvAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongGroupBySingerContract;
import com.rdc.musicplayer.musicplayer.listener.OnClickRecyclerViewListener;
import com.rdc.musicplayer.musicplayer.presenter.GetLocalSongGroupBySingerPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class LocalArtistFragment extends BaseFragment implements IGetLocalSongGroupBySingerContract.View, OnClickRecyclerViewListener {

    @BindView(R.id.rv_local_singer)
    RecyclerView mRvLocalSinger;

    private List<List<Music>> mSingerList;
    private LocalSingerRvAdapter mLocalSingerRvAdapter;

    private GetLocalSongGroupBySingerPresenterImpl mGetLocalSongGroupBySingerPresenter;

    public static LocalArtistFragment newInstance(String title) {
        LocalArtistFragment localArtistFragment = new LocalArtistFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        localArtistFragment.setArguments(bundle);
        return localArtistFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_local_artist;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mSingerList = new ArrayList<>();
        mLocalSingerRvAdapter = new LocalSingerRvAdapter(mBaseActivity);
        mLocalSingerRvAdapter.setOnRecyclerViewListener(this);
        mGetLocalSongGroupBySingerPresenter = new GetLocalSongGroupBySingerPresenterImpl(this);
        mGetLocalSongGroupBySingerPresenter.getLocalSongGroupBySinger(mBaseActivity);
    }

    private void setParams(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mRvLocalSinger.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));
        mRvLocalSinger.setItemAnimator(new DefaultItemAnimator());
        mRvLocalSinger.setAdapter(mLocalSingerRvAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onGetLocalSongGroupBySingerSuccess(Map<String, List<Music>> listMap) {
        for (Map.Entry<String, List<Music>> entry :
                listMap.entrySet()) {
            mSingerList.add(entry.getValue());
            for (Music music :
                    entry.getValue()) {
                Log.e("error", music.toString());
            }
        }
        mLocalSingerRvAdapter.updataData(mSingerList);
    }

    @Override
    public void onGetLocalSongGroupBySingerFailed(String response) {
        Log.e("error", response);
    }

    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }
}
