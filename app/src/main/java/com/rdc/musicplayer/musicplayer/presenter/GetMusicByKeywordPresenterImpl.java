package com.rdc.musicplayer.musicplayer.presenter;

import com.rdc.musicplayer.musicplayer.bean.OnLineMusic;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByKeywordContract;
import com.rdc.musicplayer.musicplayer.model.GetMusicByKeywordModelImpl;

import java.util.List;

public class GetMusicByKeywordPresenterImpl implements IGetMusicByKeywordContract.Presenter {

    private IGetMusicByKeywordContract.Model mModel;
    private IGetMusicByKeywordContract.View mView;

    public GetMusicByKeywordPresenterImpl(IGetMusicByKeywordContract.View view) {
        mView = view;
        mModel = new GetMusicByKeywordModelImpl();
    }

    @Override
    public void getMusicByKeyword(String keyword, int page) {
        mModel.getMusicByKeyword(this, keyword, page);
    }

    @Override
    public void onGetMusicByKeywordSuccess(List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList) {
        mView.onGetMusicByKeywordSuccess(contentlistBeanList);
    }

    @Override
    public void onGetMusicByKeywordFailed(String response) {
        mView.onGetMusicByKeywordFailed(response);
    }
}
