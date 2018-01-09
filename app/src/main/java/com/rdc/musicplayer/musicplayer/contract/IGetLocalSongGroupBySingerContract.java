package com.rdc.musicplayer.musicplayer.contract;

import android.content.Context;

import com.rdc.musicplayer.musicplayer.bean.Music;

import java.util.List;
import java.util.Map;

public interface IGetLocalSongGroupBySingerContract {

    interface Model {
        void getLocalSongGroupBySinger(Presenter presenter, Context context);
    }

    interface View {
        void onGetLocalSongGroupBySingerSuccess(Map<String, List<Music>> listMap);
        void onGetLocalSongGroupBySingerFailed(String response);
    }

    interface Presenter {
        void getLocalSongGroupBySinger(Context context);
        void onGetLocalSongGroupBySingerSuccess(Map<String, List<Music>> listMap);
        void onGetLocalSongGroupBySingerFailed(String response);
    }
}
