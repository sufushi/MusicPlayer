package com.rdc.musicplayer.musicplayer.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rdc.musicplayer.musicplayer.R;
import com.rdc.musicplayer.musicplayer.adapter.MusicVpAdapter;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.bean.Music;
import com.rdc.musicplayer.musicplayer.listener.OnMusicChooseListener;
import com.rdc.musicplayer.musicplayer.ui.PlayMusicActivity;
import com.rdc.musicplayer.musicplayer.utils.PlayMusicUtil;
import com.rdc.musicplayer.musicplayer.view.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.rdc.musicplayer.musicplayer.constant.Constant.FRAGMENT_TITLE;

public class CategoryFragment extends BaseFragment implements OnMusicChooseListener {

    @BindView(R.id.tl_music_category)
    TabLayout mTlMusicCategory;
    @BindView(R.id.vp_music_category)
    ViewPager mVpMusicCategory;
    @BindView(R.id.civ_music_album)
    CircleImageView mCivMusicAlbum;
    @BindView(R.id.mtv_music_name)
    MarqueeTextView mMtvMusicName;
    @BindView(R.id.iv_music_playing)
    ImageView mIvMusicPlaying;
    @BindView(R.id.iv_music_operate)
    ImageView mIvMusicOperate;
    @BindView(R.id.ll_music_category)
    LinearLayout mLlMusicCategory;

    private List<BaseFragment> mFragmentList;
    private MusicVpAdapter mMusicVpAdapter;

    public static CategoryFragment newInstance(String title) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(FRAGMENT_TITLE, title);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initData(Bundle bundle) {
        setParams(bundle);
        mFragmentList = new ArrayList<>();
        String[] category = mBaseActivity.getResources().getStringArray(R.array.music_category);
        for (String aCategory : category) {
            mFragmentList.add(MusicListFragment.newInstance(aCategory, this));
        }
        mMusicVpAdapter = new MusicVpAdapter(mBaseActivity.getSupportFragmentManager(), mFragmentList, category);
        mVpMusicCategory.setAdapter(mMusicVpAdapter);
        mTlMusicCategory.setupWithViewPager(mVpMusicCategory);
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

    @OnClick({R.id.iv_music_playing, R.id.iv_music_operate, R.id.ll_music_category})
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
            case R.id.ll_music_category:
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
        if (music.getAlbumSmall() == null) {
            mCivMusicAlbum.setImageResource(R.drawable.user_icon);
        } else {
            Glide.with(mBaseActivity).load(music.getAlbumSmall()).into(mCivMusicAlbum);
        }
    }

    @Override
    public void onMusicChoose() {
        updateBottom();
    }
}
