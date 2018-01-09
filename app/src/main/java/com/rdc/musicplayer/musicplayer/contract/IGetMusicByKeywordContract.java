package com.rdc.musicplayer.musicplayer.contract;

import com.rdc.musicplayer.musicplayer.bean.OnLineMusic;

import java.util.List;

public interface IGetMusicByKeywordContract {

    interface Model {
        void getMusicByKeyword(Presenter presenter, String keyword, int page);
    }

    interface View {
        void onGetMusicByKeywordSuccess(List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList);
        void onGetMusicByKeywordFailed(String response);
    }

    interface Presenter {
        void getMusicByKeyword(String keyword, int page);
        void onGetMusicByKeywordSuccess(List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList);
        void onGetMusicByKeywordFailed(String response);
    }
}
