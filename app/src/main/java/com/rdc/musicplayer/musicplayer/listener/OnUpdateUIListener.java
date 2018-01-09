package com.rdc.musicplayer.musicplayer.listener;

public interface OnUpdateUIListener {

    void updateProgress(int progress);
    void updatePlayState(int state);
    void updatePlayMode(int mode);
    void onUpdateThreadStart();

}
