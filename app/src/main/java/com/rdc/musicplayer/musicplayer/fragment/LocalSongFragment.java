package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.LocalSongRvAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongContract;
import com.rdc.musicplayer.musicplayer.listener.OnClickRecyclerViewListener;
import com.rdc.musicplayer.musicplayer.listener.OnMusicChooseListener;
import com.rdc.musicplayer.musicplayer.presenter.GetLocalSongPresenterImpl;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import java.util.List;

import butterknife.BindView;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class LocalSongFragment extends BaseFragment implements IGetLocalSongContract.View, OnClickRecyclerViewListener {

    @BindView(R.id.rv_local_song)
    RecyclerView mRvLocalSong;

    private List<Music> mMusicList;
    private LocalSongRvAdapter mLocalSongRvAdapter;
    private GetLocalSongPresenterImpl mGetLocalSongPresenter;

    private static OnMusicChooseListener mOnMusicChooseListener;

    public static LocalSongFragment newInstance(String title, OnMusicChooseListener listener) {
        LocalSongFragment localSongFragment = new LocalSongFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        localSongFragment.setArguments(bundle);
        mOnMusicChooseListener = listener;
        return localSongFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_local_song;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mLocalSongRvAdapter = new LocalSongRvAdapter(mBaseActivity);
        mLocalSongRvAdapter.setOnRecyclerViewListener(this);
        mGetLocalSongPresenter = new GetLocalSongPresenterImpl(this);
        mGetLocalSongPresenter.getLocalSong(mBaseActivity);
        PlayMusicUtil.getInstance().bindMusicService(mBaseActivity);
    }

    private void setParams(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mRvLocalSong.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));
        mRvLocalSong.setItemAnimator(new DefaultItemAnimator());
        mRvLocalSong.setAdapter(mLocalSongRvAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onGetLocalSongSuccess(List<Music> musicList) {
        if (musicList != null && musicList.size() > 0) {
            PlayMusicUtil.CURRENT_MUSIC_POSITION = 0;
            PlayMusicUtil.CURRENT_MUSIC_LIST = musicList;
            PlayMusicUtil.CURRENT_MUSIC = musicList.get(PlayMusicUtil.CURRENT_MUSIC_POSITION);
        }
        mMusicList = musicList;
        mLocalSongRvAdapter.updataData(mMusicList);
    }

    @Override
    public void onGetLocalSongFailed(String response) {
        showToast(response);
    }

    @Override
    public void onItemClick(int position, View view) {
        PlayMusicUtil.CURRENT_MUSIC_POSITION = position;
        PlayMusicUtil.CURRENT_MUSIC = mMusicList.get(position);
        PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PLAY;
        PlayMusicUtil.getInstance().play(mMusicList.get(position).getUrl());
        if (mOnMusicChooseListener != null) {
            mOnMusicChooseListener.onMusicChoose();
        }
    }

    @Override
    public boolean onItemLongClick(int position) {
        Log.e("error", "onItemLongClick" + position);
        return false;
    }
}
