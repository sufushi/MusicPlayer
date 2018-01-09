package com.rdc.musicplayer.musicplayer.presenter;

import android.content.Context;

import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongContract;
import com.rdc.musicplayer.musicplayer.model.GetLocalSongModelImpl;

import java.util.List;

public class GetLocalSongPresenterImpl implements IGetLocalSongContract.Presenter {

    private IGetLocalSongContract.Model mModel;
    private IGetLocalSongContract.View mView;

    public GetLocalSongPresenterImpl(IGetLocalSongContract.View view) {
        mView = view;
        mModel = new GetLocalSongModelImpl();
    }

    @Override
    public void getLocalSong(Context context) {
        mModel.getLocalSong(this, context);
    }

    @Override
    public void onGetLocalSongSuccess(List<Music> musicList) {
        mView.onGetLocalSongSuccess(musicList);
    }

    @Override
    public void onGetLocalSongFailed(String response) {
        mView.onGetLocalSongFailed(response);
    }
}
