package com.rdc.musicplayer.musicplayer.presenter;

import com.rdc.musicplayer.musicplayer.bean.MusicByCategory;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByCategoryContract;
import com.rdc.musicplayer.musicplayer.model.GetMusicByCategoryModelImpl;

import java.util.List;

public class GetMusicByCategoryPresenterImpl implements IGetMusicByCategoryContract.Presenter {

    private IGetMusicByCategoryContract.Model mModel;
    private IGetMusicByCategoryContract.View mView;

    public GetMusicByCategoryPresenterImpl(IGetMusicByCategoryContract.View view) {
        mView = view;
        mModel = new GetMusicByCategoryModelImpl();
    }

    @Override
    public void getMusicListByCategory(int category) {
        mModel.getMusicListByCategory(this, category);
    }

    @Override
    public void onGetMusicListByCategorySuccess(List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> songList) {
        mView.onGetMusicListByCategorySuccess(songList);
    }

    @Override
    public void onGetMusicListByCategoryFailed(String response) {
        mView.onGetMusicListByCategoryFailed(response);
    }
}
