package com.rdc.musicplayer.musicplayer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class ShareLocalMusicUtil {

    public static void shareLocalMusic(String path, Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        File file =new File(path);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setType("*/*");
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }

}
