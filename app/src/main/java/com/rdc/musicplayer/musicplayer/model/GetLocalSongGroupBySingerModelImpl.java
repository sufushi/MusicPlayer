package com.rdc.musicplayer.musicplayer.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongGroupBySingerContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetLocalSongGroupBySingerModelImpl implements IGetLocalSongGroupBySingerContract.Model {

    @Override
    public void getLocalSongGroupBySinger(IGetLocalSongGroupBySingerContract.Presenter presenter, Context context) {
        Map<String, List<Music>> listMap = new HashMap<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int musicId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                int albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                String musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String musicPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                Music music = new Music();
                music.setSingerId(musicId);
                music.setAlbumId(albumId);
                music.setSongName(musicName);
                music.setSingerName(artist);
                music.setUrl(musicPath);
                music.setSeconds((int) (duration / 1000));
                if (listMap.containsKey(artist)) {
                    listMap.get(artist).add(music);
                } else {
                    List<Music> musicList = new ArrayList<>();
                    musicList.add(music);
                    listMap.put(artist, musicList);
                }
            }
            cursor.close();
        }
        if (listMap.size() > 0) {
            presenter.onGetLocalSongGroupBySingerSuccess(listMap);
        } else {
            presenter.onGetLocalSongGroupBySingerFailed("没有查找到歌曲");
        }
    }
}
