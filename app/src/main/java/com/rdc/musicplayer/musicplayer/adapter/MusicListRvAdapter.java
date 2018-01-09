package com.rdc.musicplayer.musicplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.base.BaseRecyclerViewAdapter;
import com.rdc.musicplayer.musicplayer.bean.MusicByCategory;
import com.rdc.musicplayer.musicplayer.utils.TimeFormatUtil;

import butterknife.BindView;

public class MusicListRvAdapter extends BaseRecyclerViewAdapter<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MusicListRvAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_music, null);
        return new MusicListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MusicListViewHolder) holder).bindView(mDataList.get(position));
    }

    class MusicListViewHolder extends BaseRvHolder {

        @BindView(R.id.iv_music_thumbnail)
        ImageView mIvMusicThumbnail;
        @BindView(R.id.tv_song_name)
        TextView mTvSongName;
        @BindView(R.id.tv_singer_name)
        TextView mTvSingerName;
        @BindView(R.id.tv_song_duration)
        TextView mTvSongDuration;

        MusicListViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean songlistBean) {
            Glide.with(mContext).load(songlistBean.getAlbumpic_small()).into(mIvMusicThumbnail);
            mTvSongName.setText(songlistBean.getSongname());
            mTvSingerName.setText(songlistBean.getSingername());
            mTvSongDuration.setText(TimeFormatUtil.format(songlistBean.getSeconds()));
        }
    }
}
