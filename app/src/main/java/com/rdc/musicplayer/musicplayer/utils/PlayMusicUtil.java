package com.rdc.musicplayer.musicplayer.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.listener.OnStartPlayListener;
import com.rdc.musicplayer.musicplayer.listener.OnUpdateUIListener;
import com.rdc.musicplayer.musicplayer.service.MusicPlayService;

import java.util.List;

public class PlayMusicUtil {

    private static PlayMusicUtil sInstance;
    private Context mContext;

    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int STOP = 2;
    public static final int MODE_SEQUENCE = 3;
    public static final int MODE_LOOP = 4;
    public static final int MODE_RANDOM = 5;
    public static final int MODE_REPEAT = 6;
    public static final String ACTION_STOP_SERVICE = "action_stop_service";
    public static final String ACTION_UPDATE_BOTTOM_MUSIC_MSG = "action_update_bottom_music_msg";
    public static final int LOCAL_MUSIC = 7;
    public static final int ONLINE_MUSIC = 8;
    public static int CURRENT_MODE = MODE_LOOP;
    public static int CURRENT_STATE = STOP;
    public static int CURRENT_MUSIC_POSITION = -1;
    public static Music CURRENT_MUSIC;
    public static List<Music> CURRENT_MUSIC_LIST;
    private static boolean isBind;

    private MusicPlayService mMusicPlayService;
    private OnUpdateUIListener mOnUpdateUIListener;


    private PlayMusicUtil() {

    }

    public static PlayMusicUtil getInstance() {
        if (sInstance == null) {
            synchronized (PlayMusicUtil.class) {
                sInstance = new PlayMusicUtil();
            }
        }
        return sInstance;
    }

    public void setOnUpdateUIListener(OnUpdateUIListener onUpdateUIListener) {
        mOnUpdateUIListener = onUpdateUIListener;
    }

    public void bindMusicService(Context context) {
        mContext = context;
        if (!isBind) {
            Intent intent = new Intent(mContext, MusicPlayService.class);
            context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unBindMusicService(Context context) {
        if (isBind) {
            context.unbindService(mServiceConnection);
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("error", "onServiceConnected");
            MusicPlayService.MusicBinder musicBinder = (MusicPlayService.MusicBinder) service;
            mMusicPlayService = (MusicPlayService) musicBinder.getService();
            isBind = true;
            if (mOnUpdateUIListener != null) {
                mOnUpdateUIListener.onUpdateThreadStart();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    public void play() {
        if (CURRENT_MUSIC_LIST != null && CURRENT_MUSIC_LIST.size() > 0) {
            play(CURRENT_MUSIC_LIST.get(CURRENT_MUSIC_POSITION).getUrl());
        }
    }

    public void play(String path) {
        mMusicPlayService.play(path);
    }

    public void pause() {
        mMusicPlayService.pause();
    }

    public void stop() {
        mMusicPlayService.stop();
    }

    public void playNext() {
        mMusicPlayService.playNext();
    }

    public void playPre() {
        mMusicPlayService.playPre();
    }

    public void changeMode() {
        switch (CURRENT_MODE) {
            case MODE_LOOP:
                CURRENT_MODE = MODE_RANDOM;
                break;
            case MODE_RANDOM:
                CURRENT_MODE = MODE_REPEAT;
                break;
            case MODE_REPEAT:
                CURRENT_MODE = MODE_LOOP;
                break;
            default:
                break;
        }
    }

    public void setOnStartPlayListener(OnStartPlayListener listener) {
        mMusicPlayService.setOnStartPlayListener(listener);
    }

}
