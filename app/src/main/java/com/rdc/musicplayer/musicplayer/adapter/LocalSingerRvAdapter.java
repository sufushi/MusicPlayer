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

import java.util.List;

import butterknife.BindView;

public class LocalSingerRvAdapter extends BaseRecyclerViewAdapter<List<Music>> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public LocalSingerRvAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_local_singer, null);
        return new LocalSingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LocalSingerViewHolder) holder).bindView(mDataList.get(position));
    }

    class LocalSingerViewHolder extends BaseRvHolder {

        @BindView(R.id.tv_singer_name)
        TextView mTvSingerName;
        @BindView(R.id.tv_song_num)
        TextView mTvSongNum;

        LocalSingerViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(List<Music> musics) {
            mTvSingerName.setText(musics.get(0).getSingerName());
            String songNum = "(" + musics.size() + "首)";
            mTvSongNum.setText(songNum);
        }
    }
}
