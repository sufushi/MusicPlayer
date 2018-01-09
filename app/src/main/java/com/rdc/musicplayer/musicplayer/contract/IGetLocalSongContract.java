package com.rdc.musicplayer.musicplayer.contract;

import android.content.Context;

import com.rdc.musicplayer.musicplayer.bean.Music;

import java.util.List;

public interface IGetLocalSongContract {

    interface Model {
        void getLocalSong(Presenter presenter, Context context);
    }

    interface View {
        void onGetLocalSongSuccess(List<Music> musicList);
        void onGetLocalSongFailed(String response);
    }

    interface Presenter {
        void getLocalSong(Context context);
        void onGetLocalSongSuccess(List<Music> musicList);
        void onGetLocalSongFailed(String response);
    }
}
