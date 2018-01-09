package com.rdc.musicplayer.musicplayer.presenter;

import android.content.Context;

import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongGroupBySingerContract;
import com.rdc.musicplayer.musicplayer.model.GetLocalSongGroupBySingerModelImpl;

import java.util.List;
import java.util.Map;

public class GetLocalSongGroupBySingerPresenterImpl implements IGetLocalSongGroupBySingerContract.Presenter {

    private IGetLocalSongGroupBySingerContract.Model mModel;
    private IGetLocalSongGroupBySingerContract.View mView;

    public GetLocalSongGroupBySingerPresenterImpl(IGetLocalSongGroupBySingerContract.View view) {
        mView = view;
        mModel = new GetLocalSongGroupBySingerModelImpl();
    }

    @Override
    public void getLocalSongGroupBySinger(Context context) {
        mModel.getLocalSongGroupBySinger(this, context);
    }

    @Override
    public void onGetLocalSongGroupBySingerSuccess(Map<String, List<Music>> listMap) {
        mView.onGetLocalSongGroupBySingerSuccess(listMap);
    }

    @Override
    public void onGetLocalSongGroupBySingerFailed(String response) {
        mView.onGetLocalSongGroupBySingerFailed(response);
    }
}
