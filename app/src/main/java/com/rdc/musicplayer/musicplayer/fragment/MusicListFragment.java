package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.MusicListRvAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.bean.MusicByCategory;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByCategoryContract;
import com.rdc.musicplayer.musicplayer.listener.OnClickRecyclerViewListener;
import com.rdc.musicplayer.musicplayer.listener.OnMusicChooseListener;
import com.rdc.musicplayer.musicplayer.presenter.GetMusicByCategoryPresenterImpl;
import com.rdc.musicplayer.musicplayer.ui.PlayMusicActivity;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_HONGKANG;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_HOT;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_INLAND;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_JAPAN;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_KOREA;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_ROCK;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_SALES;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_VOLKSLIED;
import static com.rdc.musicplayer.musicplayer.constant.Constant.MUSIC_WEST;

public class MusicListFragment extends BaseFragment implements IGetMusicByCategoryContract.View, SwipeRefreshLayout.OnRefreshListener, OnClickRecyclerViewListener {

    @BindView(R.id.rv_music_list)
    RecyclerView mRvMusicList;
    @BindView(R.id.srl_music_list)
    SwipeRefreshLayout mSrlMusicList;

    private String mCategory;

    private GetMusicByCategoryPresenterImpl mGetMusicByCategoryPresenter;
    private List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> mSonglistBeanList;
    private MusicListRvAdapter mMusicListRvAdapter;

    private List<Music> mMusicList;

    private static OnMusicChooseListener mOnMusicChooseListener;

    public static MusicListFragment newInstance(String title, OnMusicChooseListener listener) {
        MusicListFragment musicListFragment = new MusicListFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        musicListFragment.setArguments(bundle);
        mOnMusicChooseListener = listener;
        return musicListFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mGetMusicByCategoryPresenter = new GetMusicByCategoryPresenterImpl(this);
        mCategory = bundle.getString(FRAGMENT_TITLE);
        mMusicListRvAdapter = new MusicListRvAdapter(mBaseActivity);
        mMusicList = new ArrayList<>();
        fetchData();
    }

    private void fetchData() {
        String index;
        if (mCategory != null) {
            switch (mCategory) {
                case "欧美":
                    index = MUSIC_WEST;
                    break;
                case "内地":
                    index = MUSIC_INLAND;
                    break;
                case "港台":
                    index = MUSIC_HONGKANG;
                    break;
                case "韩国":
                    index = MUSIC_KOREA;
                    break;
                case "日本":
                    index = MUSIC_JAPAN;
                    break;
                case "民谣":
                    index = MUSIC_VOLKSLIED;
                    break;
                case "摇滚":
                    index = MUSIC_ROCK;
                    break;
                case "销量":
                    index = MUSIC_SALES;
                    break;
                case "热门":
                    index = MUSIC_HOT;
                    break;
                default:
                    index = MUSIC_WEST;
                    break;
            }
            mGetMusicByCategoryPresenter.getMusicListByCategory(Integer.valueOf(index));
        }
    }

    private void setParams(Bundle bundle) {

    }

    @Override
    protected void initView() {
        mRvMusicList.setLayoutManager(new LinearLayoutManager(mBaseActivity, LinearLayoutManager.VERTICAL, false));
        mRvMusicList.setItemAnimator(new DefaultItemAnimator());
        mRvMusicList.setAdapter(mMusicListRvAdapter);
        mSrlMusicList.setColorSchemeResources(R.color.colorPrimary, R.color.yellow, R.color.lightblue, R.color.blueviolet);
    }

    @Override
    protected void setListener() {
        mMusicListRvAdapter.setOnRecyclerViewListener(this);
        mSrlMusicList.setOnRefreshListener(this);
    }

    @Override
    public void onGetMusicListByCategorySuccess(List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> songList) {
        mSonglistBeanList = songList;
        mMusicListRvAdapter.updataData(mSonglistBeanList);
        if (mSrlMusicList != null) {
            mSrlMusicList.setRefreshing(false);
        }
        for (MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean songlistBean : mSonglistBeanList) {
            int songid = songlistBean.getSongid();
            int singerid = songlistBean.getSingerid();
            int albumid = songlistBean.getAlbumid();
            int seconds = songlistBean.getSeconds();
            String songname = songlistBean.getSongname();
            String singername = songlistBean.getSingername();
            String albumBig = songlistBean.getAlbumpic_big();
            String albumSmall = songlistBean.getAlbumpic_small();
            String url = songlistBean.getUrl();
            Music music = new Music(songid, singerid, albumid, seconds, songname, singername, albumBig, albumSmall, url);
            mMusicList.add(music);
        }
    }

    @Override
    public void onGetMusicListByCategoryFailed(String response) {
        if (mSrlMusicList != null) {
            mSrlMusicList.setRefreshing(false);
        }
        Log.e("error", "onGetMusicListByCategoryFailed" + response);
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    @Override
    public void onItemClick(int position, View view) {
        PlayMusicUtil.getInstance().bindMusicService(mBaseActivity);
        PlayMusicUtil.CURRENT_MUSIC_LIST = mMusicList;
        PlayMusicUtil.CURRENT_MUSIC = mMusicList.get(position);
        PlayMusicUtil.CURRENT_MUSIC_POSITION = position;
        PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PLAY;
        PlayMusicUtil.getInstance().play(mSonglistBeanList.get(position).getUrl());
        if (mOnMusicChooseListener != null) {
            mOnMusicChooseListener.onMusicChoose();
        }
//        startActivity(PlayMusicActivity.class);
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }
}
