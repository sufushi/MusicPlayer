package com.rdc.musicplayer.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.rdc.musicplayer.musicplayer.listener.OnStartPlayListener;
import com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import java.io.IOException;
import java.util.Random;

public class MusicPlayService extends Service {

    public static MediaPlayer sMediaPlayer = null;
    private OnStartPlayListener mOnStartPlayListener;

    public void setOnStartPlayListener(OnStartPlayListener onStartPlayListener) {
        mOnStartPlayListener = onStartPlayListener;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (sMediaPlayer == null) {
            sMediaPlayer = new MediaPlayer();
        }
        sMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                sMediaPlayer.start();
                PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PLAY;
                MusicNotificationUtil.showMusicNotification(MusicPlayService.this);
                if (mOnStartPlayListener != null) {
                    mOnStartPlayListener.onCompletedStartPlay();
                }
                // TODO: 2017/11/1
            }
        });
        sMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playByMode();
                if (mOnStartPlayListener != null) {
                    mOnStartPlayListener.onCompletedStartPlay();
                }
            }
        });
        sMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private void playByMode() {
        switch (PlayMusicUtil.CURRENT_MODE) {
            case PlayMusicUtil.MODE_SEQUENCE:
                playSequence();
                break;
            case PlayMusicUtil.MODE_LOOP:
                playLoop();
                break;
            case PlayMusicUtil.MODE_RANDOM:
                playRandom();
                break;
            case PlayMusicUtil.MODE_REPEAT:
                playRepeat();
                break;
            default:
                break;
        }
    }

    private void playRepeat() {
        play(PlayMusicUtil.CURRENT_MUSIC.getUrl());
    }

    private void playRandom() {
        int size = PlayMusicUtil.CURRENT_MUSIC_LIST.size();
        PlayMusicUtil.CURRENT_MUSIC_POSITION = new Random(PlayMusicUtil.CURRENT_MUSIC_POSITION).nextInt(size);
        PlayMusicUtil.CURRENT_MUSIC = PlayMusicUtil.CURRENT_MUSIC_LIST.get(PlayMusicUtil.CURRENT_MUSIC_POSITION);
        play(PlayMusicUtil.CURRENT_MUSIC.getUrl());
    }

    private void playLoop() {
        int size = PlayMusicUtil.CURRENT_MUSIC_LIST.size();
        PlayMusicUtil.CURRENT_MUSIC_POSITION = (PlayMusicUtil.CURRENT_MUSIC_POSITION + 1) % size;
        PlayMusicUtil.CURRENT_MUSIC = PlayMusicUtil.CURRENT_MUSIC_LIST.get(PlayMusicUtil.CURRENT_MUSIC_POSITION);
        play(PlayMusicUtil.CURRENT_MUSIC.getUrl());
    }

    private void playSequence() {
        int size = PlayMusicUtil.CURRENT_MUSIC_LIST.size();
        int position = ++ PlayMusicUtil.CURRENT_MUSIC_POSITION ;
        if (position < size) {
            PlayMusicUtil.CURRENT_MUSIC = PlayMusicUtil.CURRENT_MUSIC_LIST.get(position);
            play(PlayMusicUtil.CURRENT_MUSIC.getUrl());
        } else {
            PlayMusicUtil.CURRENT_MUSIC_POSITION --;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public void onDestroy() {
        if (sMediaPlayer != null && sMediaPlayer.isPlaying()) {
            sMediaPlayer.stop();
            sMediaPlayer.release();
            sMediaPlayer = null;
        }
        super.onDestroy();
    }

    public void play(String path) {
        if (sMediaPlayer == null) {
            init();
        }
        if (PlayMusicUtil.CURRENT_STATE != PlayMusicUtil.STOP) {
            sMediaPlayer.stop();
        }
        sMediaPlayer.reset();
        try {
            sMediaPlayer.setDataSource(path);
            sMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (sMediaPlayer != null) {
            if (sMediaPlayer.isPlaying()) {
                sMediaPlayer.pause();
                PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PAUSE;
            } else {
                sMediaPlayer.start();
                PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.PLAY;
            }
        }
    }

    public void stop() {
        if (sMediaPlayer != null) {
            sMediaPlayer.stop();
            sMediaPlayer.release();
            sMediaPlayer = null;
            PlayMusicUtil.CURRENT_STATE = PlayMusicUtil.STOP;
        }
    }

    public void playNext() {
        stop();
        playByMode();
    }

    public void playPre() {
        switch (PlayMusicUtil.CURRENT_MODE) {
            case PlayMusicUtil.MODE_LOOP:
            case PlayMusicUtil.MODE_REPEAT:
                int size = PlayMusicUtil.CURRENT_MUSIC_LIST.size();
                PlayMusicUtil.CURRENT_MUSIC_POSITION = -- PlayMusicUtil.CURRENT_MUSIC_POSITION;
                if (PlayMusicUtil.CURRENT_MUSIC_POSITION < 0) {
                    PlayMusicUtil.CURRENT_MUSIC_POSITION = size - 1;
                }
                PlayMusicUtil.CURRENT_MUSIC = PlayMusicUtil.CURRENT_MUSIC_LIST.get(PlayMusicUtil.CURRENT_MUSIC_POSITION);
                play(PlayMusicUtil.CURRENT_MUSIC.getUrl());
                break;
            case PlayMusicUtil.MODE_RANDOM:
                playRandom();
                break;
            default:
                break;
        }
    }

    public class MusicBinder extends Binder {

        public Service getService() {
            return MusicPlayService.this;
        }
    }
}
