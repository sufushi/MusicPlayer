package com.rdc.musicplayer.musicplayer.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenUtil {

    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
