package com.rdc.musicplayer.musicplayer.presenter;

import com.rdc.musicplayer.musicplayer.contract.IGetMusicLrcContract;
import com.rdc.musicplayer.musicplayer.model.GetMusicLrcModelImpl;

public class GetMusicLrcPresenterImpl implements IGetMusicLrcContract.Presenter {

    private IGetMusicLrcContract.Model mModel;
    private IGetMusicLrcContract.View mView;

    public GetMusicLrcPresenterImpl(IGetMusicLrcContract.View view) {
        mView = view;
        mModel = new GetMusicLrcModelImpl();
    }

    @Override
    public void getLyrics(int songId) {
        mModel.getLyrics(this, songId);
    }

    @Override
    public void onGetOnlineLyricsSuccess(String lrc) {
        mView.onGetOnlineLyricsSuccess(lrc);
    }

    @Override
    public void onGetOnlineLyricsFailed(String response) {
        mView.onGetOnlineLyricsFailed(response);
    }
}
