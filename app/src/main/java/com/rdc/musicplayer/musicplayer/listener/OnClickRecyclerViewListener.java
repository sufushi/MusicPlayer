package com.rdc.musicplayer.musicplayer.listener;


import android.view.View;

public interface OnClickRecyclerViewListener {
    void onItemClick(int position, View view);
    boolean onItemLongClick(int position);
}
