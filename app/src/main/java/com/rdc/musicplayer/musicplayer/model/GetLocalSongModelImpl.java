package com.rdc.musicplayer.musicplayer.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.contract.IGetLocalSongContract;

import java.util.ArrayList;
import java.util.List;

public class GetLocalSongModelImpl implements IGetLocalSongContract.Model {

    @Override
    public void getLocalSong(IGetLocalSongContract.Presenter presenter, final Context context) {
        final List<Music> musicList = new ArrayList<>();
        queryLocalMusic(context, musicList);
        if (musicList.size() > 0) {
            presenter.onGetLocalSongSuccess(musicList);
        } else {
            presenter.onGetLocalSongFailed("没有本地歌曲");
        }
    }

    private void queryLocalMusic(Context context, List<Music> musicList) {
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
//                Bitmap thumbnail = GetThumbnailUtil.createAlbumThumbnail(musicPath);
                Music music = new Music();
                music.setSongId(-1);
                music.setAlbumId(albumId);
                music.setSongName(musicName);
                music.setSingerName(artist);
                music.setUrl(musicPath);
                music.setSeconds((int) (duration / 1000));
//                music.setThumbnail(thumbnail);
                musicList.add(music);
            }
            cursor.close();
        }
    }

    private Bitmap getThumbnail(int album_id, Context context) {
        String uriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        String album_art = null;
        Cursor cursor = context.getContentResolver().query(Uri.parse(uriAlbums + "/" + Integer.toString(album_id)), projection, null, null, null);
        if (cursor != null && cursor.getCount() > 0 && cursor.getColumnCount() > 0) {
            cursor.moveToNext();
            album_art = cursor.getString(0);
            Log.e("error", "album_art:" + album_art);
            cursor.close();
            cursor = null;
        } else {
            Log.e("error", "cursor == null");
        }
        if (album_art != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(album_art);
            return bitmap;
        } else {
            return null;
        }
    }
}
