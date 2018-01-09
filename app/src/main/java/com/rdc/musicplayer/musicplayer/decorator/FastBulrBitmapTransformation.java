package com.rdc.musicplayer.musicplayer.decorator;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.rdc.musicplayer.musicplayer.utils.FastBulr;

import java.security.MessageDigest;

public class FastBulrBitmapTransformation extends BitmapTransformation {

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.1f, 0.1f);
        Bitmap overlay = Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
        return FastBulr.doBlur(overlay, 5, true);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
