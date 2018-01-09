package com.rdc.musicplayer.musicplayer.contract;

public interface IGetMusicLrcContract {

    interface Model {
        void getLyrics(Presenter presenter, int songId);
    }

    interface View {
        void onGetOnlineLyricsSuccess(String lrc);
        void onGetOnlineLyricsFailed(String response);
    }

    interface Presenter {
        void getLyrics(int songId);
        void onGetOnlineLyricsSuccess(String lrc);
        void onGetOnlineLyricsFailed(String response);
    }
}
