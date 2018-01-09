package com.rdc.musicplayer.musicplayer.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.OnlineMusicRvAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseActivity;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.bean.OnLineMusic;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByKeywordContract;
import com.rdc.musicplayer.musicplayer.listener.OnClickRecyclerViewListener;
import com.rdc.musicplayer.musicplayer.presenter.GetMusicByKeywordPresenterImpl;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OnLineMusicActivity extends BaseActivity implements IGetMusicByKeywordContract.View, OnClickRecyclerViewListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tb_title)
    Toolbar mTbTitle;
    @BindView(R.id.rv_online_music)
    RecyclerView mRvOnlineMusic;
    @BindView(R.id.srl_online_music)
    SwipeRefreshLayout mSrlOnlineMusic;

    private String mKeyword = "";

    private OnlineMusicRvAdapter mOnlineMusicRvAdapter;
    private List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mContentlistBeanList;
    private List<Music> mMusicList;
    private GetMusicByKeywordPresenterImpl mGetMusicByKeywordPresenter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_online_music;
    }

    @Override
    protected void initData() {
        mOnlineMusicRvAdapter = new OnlineMusicRvAdapter(this);
        mMusicList = new ArrayList<>();
        mGetMusicByKeywordPresenter = new GetMusicByKeywordPresenterImpl(this);
        mKeyword = getIntent().getStringExtra("keyword");
        mGetMusicByKeywordPresenter.getMusicByKeyword(mKeyword, 1);
    }

    @Override
    protected void initView() {
        mTbTitle.setTitle("搜索结果");
        setSupportActionBar(mTbTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTbTitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRvOnlineMusic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvOnlineMusic.setItemAnimator(new DefaultItemAnimator());
        mRvOnlineMusic.setAdapter(mOnlineMusicRvAdapter);
        mSrlOnlineMusic.setColorSchemeResources(R.color.colorPrimary, R.color.yellow, R.color.lightblue, R.color.blueviolet);
    }

    @Override
    protected void initListener() {
        mOnlineMusicRvAdapter.setOnRecyclerViewListener(this);
        mSrlOnlineMusic.setOnRefreshListener(this);
    }

    @Override
    public void onGetMusicByKeywordSuccess(List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList) {
        mContentlistBeanList = contentlistBeanList;
        mOnlineMusicRvAdapter.updataData(mContentlistBeanList);
        if (mSrlOnlineMusic != null) {
            mSrlOnlineMusic.setRefreshing(false);
        }
        for (OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean : mContentlistBeanList) {
            int songid = contentlistBean.getSongid();
            int singerid = contentlistBean.getSingerid();
            int albumid = contentlistBean.getAlbumid();
            int seconds = -1;
            String songname = contentlistBean.getSongname();
            String singername = contentlistBean.getSingername();
            String albumBig = contentlistBean.getAlbumpic_big();
            String albumSmall = contentlistBean.getAlbumpic_small();
            String url = contentlistBean.getM4a();
            Music music = new Music(songid, singerid, albumid, seconds, songname, singername, albumBig, albumSmall, url);
            mMusicList.add(music);
        }
    }

    @Override
    public void onGetMusicByKeywordFailed(String response) {
        if (mSrlOnlineMusic != null) {
            mSrlOnlineMusic.setRefreshing(false);
        }
        Log.e("error", "onGetMusicByKeywordFailed" + response);
    }

    @Override
    public void onItemClick(int position, View view) {
        PlayMusicUtil.getInstance().bindMusicService(this);
        PlayMusicUtil.CURRENT_MUSIC_LIST = mMusicList;
        PlayMusicUtil.CURRENT_MUSIC = mMusicList.get(position);
        PlayMusicUtil.CURRENT_MUSIC_POSITION = position;
        PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PLAY;
        PlayMusicUtil.getInstance().play(mContentlistBeanList.get(position).getM4a());
        startActivity(PlayMusicActivity.class);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    @Override
    public void onRefresh() {
        mGetMusicByKeywordPresenter.getMusicByKeyword(mKeyword, 1);
    }
}
