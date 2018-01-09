package com.rdc.musicplayer.musicplayer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;

import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.ACTION_BUTTON;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.BUTTON_CLOSE;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.BUTTON_LRC;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.BUTTON_NEXT;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.BUTTON_PLAY;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.BUTTON_PRE;
import static com.rdc.musicplayer.musicplayer.utils.MusicNotificationUtil.INTENT_BUTTON_TAG;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ACTION_BUTTON)) {
            switch (intent.getIntExtra(INTENT_BUTTON_TAG, 0)) {
                case BUTTON_PRE:
                    PlayMusicUtil.getInstance().playPre();
                    break;
                case BUTTON_PLAY:
                    PlayMusicUtil.getInstance().pause();
                    MusicNotificationUtil.updateNotification();
                    break;
                case BUTTON_NEXT:
                    PlayMusicUtil.getInstance().playNext();
                    break;
                case BUTTON_LRC:
                    break;
                case BUTTON_CLOSE:
                    break;
                default:
                    break;
            }
        }

    }
}
