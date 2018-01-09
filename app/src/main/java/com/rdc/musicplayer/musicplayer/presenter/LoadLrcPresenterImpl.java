package com.rdc.musicplayer.musicplayer.presenter;

import com.rdc.musicplayer.musicplayer.contract.ILoadLrcContract;
import com.rdc.musicplayer.musicplayer.model.LoadLrcModelImpl;

public class LoadLrcPresenterImpl implements ILoadLrcContract.Presenter {

    private ILoadLrcContract.Model mModel;
    private ILoadLrcContract.View mView;

    public LoadLrcPresenterImpl(ILoadLrcContract.View view) {
        mView = view;
        mModel = new LoadLrcModelImpl();
    }

    @Override
    public void getLyrics() {
        mModel.getLyrics(this);
    }

    @Override
    public void onGetLyricsSuccess(String lrc) {
        mView.onGetLyricsSuccess(lrc);
    }

    @Override
    public void onGetLyricsFailed(String response) {
        mView.onGetLyricsFailed(response);
    }
}
