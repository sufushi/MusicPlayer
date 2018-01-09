package com.rdc.musicplayer.musicplayer.model;

import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.Lyrics;
import com.rdc.musicplayer.musicplayer.contract.ILoadLrcContract;
import com.rdc.musicplayer.musicplayer.utils.LrcUtil;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import java.util.ArrayList;
import java.util.List;

public class LoadLrcModelImpl implements ILoadLrcContract.Model {

    @Override
    public void getLyrics(ILoadLrcContract.Presenter presenter) {
//        List<Lyrics> lyricsList = new ArrayList<>();
//        String lrcs = "";
//        if (PlayMusicUtil.CURRENT_MUSIC != null) {
//            lyricsList = LrcUtil.loadLrc(PlayMusicUtil.CURRENT_MUSIC);
//        }
//        if (lyricsList != null && lyricsList.size() > 0) {
//            for (Lyrics lyrics :
//                    lyricsList) {
//                lrcs += lyrics.getLrc();
//                Log.e("error", lyrics.getLrc());
//            }
//        }
        String lrcs = "";
        if (PlayMusicUtil.CURRENT_MUSIC != null) {
            lrcs = LrcUtil.loadLyrics(PlayMusicUtil.CURRENT_MUSIC);
        }

        presenter.onGetLyricsSuccess(lrcs);
    }
}
