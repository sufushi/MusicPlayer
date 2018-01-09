package com.rdc.musicplayer.musicplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseRecyclerViewAdapter;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.utils.TimeFormatUtil;


import butterknife.BindView;

public class LocalSongRvAdapter extends BaseRecyclerViewAdapter<Music> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public LocalSongRvAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_local_song, null);
        return new LocalSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LocalSongViewHolder) holder).bindView(mDataList.get(position));
    }

    class LocalSongViewHolder extends BaseRvHolder {

        @BindView(R.id.tv_song_name)
        TextView mTvSongName;
        @BindView(R.id.tv_singer_name)
        TextView mTvSingerName;
        @BindView(R.id.tv_song_duration)
        TextView mTvSongDuration;

        LocalSongViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Music music) {
            mTvSongName.setText(music.getSongName());
            mTvSingerName.setText(music.getSingerName());
            mTvSongDuration.setText(TimeFormatUtil.format(music.getSeconds()));
        }
    }
}
