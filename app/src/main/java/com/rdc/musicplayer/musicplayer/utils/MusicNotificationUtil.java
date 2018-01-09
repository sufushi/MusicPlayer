package com.rdc.musicplayer.musicplayer.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rdc.musicplayer.musicplayer.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MusicNotificationUtil {

    public static final String ACTION_BUTTON = "action_button";
    public static final String INTENT_BUTTON_TAG = "intent_button_tag";
    public static final int BUTTON_PRE = 1;
    public static final int BUTTON_PLAY = 2;
    public static final int BUTTON_NEXT = 3;
    public static final int BUTTON_LRC = 4;
    public static final int BUTTON_CLOSE = 5;
    private static NotificationManager notificationManager;
    private static RemoteViews remoteViews;
    private static Notification.Builder builder;
    private static Notification notification;

    public static void showMusicNotification(Context context) {

        builder = new Notification.Builder(context);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_music_notification);
        remoteViews.setTextViewText(R.id.tv_music_name, PlayMusicUtil.CURRENT_MUSIC.getSongName());
        if (PlayMusicUtil.CURRENT_MUSIC.getAlbumSmall() != null) {
            Glide.with(context).load(PlayMusicUtil.CURRENT_MUSIC.getAlbumSmall())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            remoteViews.setImageViewBitmap(R.id.iv_music_thumbnail, ((BitmapDrawable) resource).getBitmap());
                        }
                    });
        }

        Intent intent = new Intent(ACTION_BUTTON);
        intent.putExtra(INTENT_BUTTON_TAG, BUTTON_PRE);
        PendingIntent PreIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_nf_btn_pre, PreIntent);

        intent.putExtra(INTENT_BUTTON_TAG, BUTTON_PLAY);
        PendingIntent PlayIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_nf_btn_pause, PlayIntent);

        intent.putExtra(INTENT_BUTTON_TAG, BUTTON_NEXT);
        PendingIntent NextIntent = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_nf_btn_next, NextIntent);

        intent.putExtra(INTENT_BUTTON_TAG, BUTTON_LRC);
        PendingIntent LrcIntent = PendingIntent.getBroadcast(context, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_nf_btn_lrc, LrcIntent);

        intent.putExtra(INTENT_BUTTON_TAG, BUTTON_CLOSE);
        PendingIntent CloseIntent = PendingIntent.getBroadcast(context, 4, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_nf_btn_close, CloseIntent);

        builder.setContent(remoteViews).setSmallIcon(R.drawable.ic_logo);
        notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager = ((NotificationManager) context.getSystemService(NOTIFICATION_SERVICE));
        notificationManager.notify(1, notification);
    }

    public static void updateNotification() {
        if (PlayMusicUtil.CURRENT_STATE == PlayMusicUtil.PLAY) {
            remoteViews.setImageViewResource(R.id.iv_nf_btn_pause, R.drawable.ic_action_pause);
        } else {
            remoteViews.setImageViewResource(R.id.iv_nf_btn_pause, R.drawable.ic_action_playing);
        }
        builder.setContent(remoteViews);
        notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(1, notification);
    }

}
