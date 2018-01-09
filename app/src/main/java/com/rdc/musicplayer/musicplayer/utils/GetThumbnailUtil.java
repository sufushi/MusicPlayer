package com.rdc.musicplayer.musicplayer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

public class GetThumbnailUtil {

    public static Bitmap createAlbumThumbnail(String path) {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(path);
            byte[] art = mediaMetadataRetriever.getEmbeddedPicture();
            bitmap = BitmapFactory.decodeByteArray(art, 0, art.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mediaMetadataRetriever.release();
        }
        return bitmap;
    }

}
