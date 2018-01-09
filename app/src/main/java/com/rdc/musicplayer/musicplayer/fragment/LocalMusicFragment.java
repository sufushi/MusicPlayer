package com.rdc.musicplayer.musicplayer.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.MusicVpAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.listener.OnMusicChooseListener;
import com.rdc.musicplayer.musicplayer.ui.PlayMusicActivity;
import com.rdc.musicplayer.musicplayer.utils.GetThumbnailUtil;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;
import com.rdc.musicplayer.musicplayer.view.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class LocalMusicFragment extends BaseFragment implements OnMusicChooseListener {

    @BindView(R.id.tl_local_music)
    TabLayout mTlLocalMusic;
    @BindView(R.id.vp_local_music)
    ViewPager mVpLocalMusic;
    @BindView(R.id.civ_music_album)
    CircleImageView mCivMusicAlbum;
    @BindView(R.id.mtv_music_name)
    MarqueeTextView mMtvMusicName;
    @BindView(R.id.iv_music_playing)
    ImageView mIvMusicPlaying;
    @BindView(R.id.iv_music_operate)
    ImageView mIvMusicOperate;
    @BindView(R.id.ll_local_music)
    LinearLayout mLlLocalMusic;

    private List<BaseFragment> mFragmentList;
    private MusicVpAdapter mMusicVpAdapter;

    public static LocalMusicFragment newInstance(String title) {
        LocalMusicFragment localMusicFragment = new LocalMusicFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        localMusicFragment.setArguments(bundle);
        return localMusicFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_local_music;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(LocalSongFragment.newInstance(mBaseActivity.getResources().getString(R.string.string_song), this));
        mFragmentList.add(LocalArtistFragment.newInstance(mBaseActivity.getResources().getString(R.string.string_artist)));
        String[] titles = mBaseActivity.getResources().getStringArray(R.array.local_music_array);
        mMusicVpAdapter = new MusicVpAdapter(mBaseActivity.getSupportFragmentManager(), mFragmentList, titles);
        mVpLocalMusic.setAdapter(mMusicVpAdapter);
        mTlLocalMusic.setupWithViewPager(mVpLocalMusic);
        PlayMusicUtil.getInstance().bindMusicService(mBaseActivity);
    }

    private void setParams(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onResume() {
        if (PlayMusicUtil.CURRENT_MUSIC != null) {
            updateBottom();
        }
        super.onResume();
    }

    @OnClick({R.id.iv_music_playing, R.id.iv_music_operate, R.id.ll_local_music})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_music_playing:
                if (PlayMusicUtil.CURRENT_STATE == PlayMusicUtil.STOP) {
                    if (PlayMusicUtil.CURRENT_MUSIC_POSITION < 0) {
                        Toast.makeText(mBaseActivity, "暂无歌曲", Toast.LENGTH_SHORT).show();
                    } else {
                        PlayMusicUtil.getInstance().play();
                    }
                } else {
                    PlayMusicUtil.getInstance().pause();
                }
                updateBottom();
                break;
            case R.id.iv_music_operate:
                break;
            case R.id.ll_local_music:
                startActivity(PlayMusicActivity.class);
                break;
        }
    }

    private void updateBottom() {
        if (PlayMusicUtil.CURRENT_STATE == PlayMusicUtil.PLAY) {
            mIvMusicPlaying.setImageResource(R.drawable.ic_action_pause);
        } else {
            mIvMusicPlaying.setImageResource(R.drawable.ic_action_playing);
        }
        Music music = PlayMusicUtil.CURRENT_MUSIC;
        mMtvMusicName.setText(music.getSongName());
        Bitmap thumbnail = GetThumbnailUtil.createAlbumThumbnail(music.getUrl());
        if (thumbnail == null) {
            Log.e("error", "thumbnail == null");
            mCivMusicAlbum.setImageResource(R.drawable.user_icon);
        } else {
            Log.e("error", "thumbnail != null");
            mCivMusicAlbum.setImageDrawable(new BitmapDrawable(thumbnail));
        }
    }

    @Override
    public void onMusicChoose() {
        updateBottom();
    }
}
