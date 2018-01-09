package com.rdc.musicplayer.musicplayer.contract;

import com.rdc.musicplayer.musicplayer.bean.MusicByCategory;

import java.util.List;

public interface IGetMusicByCategoryContract {

    interface Model {
        void getMusicListByCategory(Presenter presenter, int category);
    }

    interface View {
        void onGetMusicListByCategorySuccess(List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> songList);
        void onGetMusicListByCategoryFailed(String response);
    }

    interface Presenter {
        void getMusicListByCategory(int category);
        void onGetMusicListByCategorySuccess(List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> songList);
        void onGetMusicListByCategoryFailed(String response);
    }
}
