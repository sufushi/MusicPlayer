package com.rdc.musicplayer.musicplayer.contract;

public interface ILoadLrcContract {
    interface Model {
        void getLyrics(Presenter presenter);
    }

    interface View {
        void onGetLyricsSuccess(String lrc);
        void onGetLyricsFailed(String response);
    }

    interface Presenter {
        void getLyrics();
        void onGetLyricsSuccess(String lrc);
        void onGetLyricsFailed(String response);
    }
}
