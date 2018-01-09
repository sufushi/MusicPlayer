package com.rdc.musicplayer.musicplayer.utils;

import android.media.MediaPlayer;

import com.rdc.musicplayer.musicplayer.bean.Lyrics;

import java.util.List;

public class GetCurLrcUtil {

    private static int currentPosition;

    public static String getCurLrc(MediaPlayer mediaPlayer, List<Lyrics> lyricsList) {
        getCurrentPosition(mediaPlayer, lyricsList);
        return lyricsList.get(currentPosition).getLrc();
    }

    private static void getCurrentPosition(MediaPlayer mediaPlayer, List<Lyrics> lyricsList) {
        try {
            int currentMillis = mediaPlayer.getCurrentPosition();
            if (currentMillis < lyricsList.get(0).getStart()) {
                currentPosition = 0;
                return;
            }
            if (currentMillis > lyricsList.get(lyricsList.size() - 1).getStart()) {
                currentPosition = lyricsList.size() - 1;
                return;
            }
            for (int i = 0; i < lyricsList.size(); i++) {
                if (currentMillis >= lyricsList.get(i).getStart() && currentMillis < lyricsList.get(i).getEnd()) {
                    currentPosition = i;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
