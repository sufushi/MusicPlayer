package com.rdc.musicplayer.musicplayer;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.rdc.musicplayer.musicplayer.base.BaseActivity;
import com.rdc.musicplayer.musicplayer.base.BaseFragment;
import com.rdc.musicplayer.musicplayer.fragment.AboutFragment;
import com.rdc.musicplayer.musicplayer.fragment.CategoryFragment;
import com.rdc.musicplayer.musicplayer.fragment.CollectionFragment;
import com.rdc.musicplayer.musicplayer.fragment.LocalMusicFragment;
import com.rdc.musicplayer.musicplayer.fragment.RecentPlayFragment;
import com.rdc.musicplayer.musicplayer.fragment.SettingFragment;
import com.rdc.musicplayer.musicplayer.fragment.ShareFragment;
import com.rdc.musicplayer.musicplayer.ui.SearchMusicActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private FragmentManager mFragmentManager;
    private BaseFragment mCurrentFragment;

    private LocalMusicFragment mLocalMusicFragment;
    private RecentPlayFragment mRecentPlayFragment;
    private CategoryFragment mCategoryFragment;
    private CollectionFragment mCollectionFragment;
//    private ShareFragment mShareFragment;
    private SettingFragment mSettingFragment;
    private AboutFragment mAboutFragment;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initPermission();
    }

    private void initPermission() {
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {
                initFragment();
            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, permissions);
    }

    @Override
    protected void initView() {

    }

    private void initFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLocalMusicFragment = LocalMusicFragment.newInstance(getResources().getString(R.string.string_local_music));
        fragmentTransaction.add(R.id.fl_content, mLocalMusicFragment);
        fragmentTransaction.commit();
        mCurrentFragment = mLocalMusicFragment;
    }

    @Override
    protected void initListener() {
        mToolbar.setTitle(getResources().getString(R.string.string_local_music));
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        mNavView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            fragmentTransaction.hide(mCurrentFragment);
        }
        switch (item.getItemId()) {
            case R.id.nav_local_music:
                if (mLocalMusicFragment == null) {
                    mLocalMusicFragment = LocalMusicFragment.newInstance(getResources().getString(R.string.string_local_music));
                    fragmentTransaction.add(R.id.fl_content, mLocalMusicFragment);
                } else {
                    fragmentTransaction.show(mLocalMusicFragment);
                }
                mCurrentFragment = mLocalMusicFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_local_music));
                break;
            case R.id.nav_recent_play:
                if (mRecentPlayFragment == null) {
                    mRecentPlayFragment = RecentPlayFragment.newInstance(getResources().getString(R.string.string_recent_play));
                    fragmentTransaction.add(R.id.fl_content, mRecentPlayFragment);
                } else {
                    fragmentTransaction.show(mRecentPlayFragment);
                }
                mCurrentFragment = mRecentPlayFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_recent_play));
                break;
            case R.id.nav_category:
                if (mCategoryFragment == null) {
                    mCategoryFragment = CategoryFragment.newInstance(getResources().getString(R.string.string_category));
                    fragmentTransaction.add(R.id.fl_content, mCategoryFragment);
                } else {
                    fragmentTransaction.show(mCategoryFragment);
                }
                mCurrentFragment = mCategoryFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_category));
                break;
            case R.id.nav_collection:
                if (mCollectionFragment == null) {
                    mCollectionFragment = CollectionFragment.newInstance(getResources().getString(R.string.string_collection));
                    fragmentTransaction.add(R.id.fl_content, mCollectionFragment);
                } else {
                    fragmentTransaction.show(mCollectionFragment);
                }
                mCurrentFragment = mCollectionFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_collection));
                break;
//            case R.id.nav_share:
//                if (mShareFragment == null) {
//                    mShareFragment = ShareFragment.newInstance(getResources().getString(R.string.string_share));
//                    fragmentTransaction.add(R.id.fl_content, mShareFragment);
//                } else {
//                    fragmentTransaction.show(mShareFragment);
//                }
//                mCurrentFragment = mShareFragment;
//                mToolbar.setTitle(getResources().getString(R.string.string_share));
//                break;
            case R.id.nav_setting:
                if (mSettingFragment == null) {
                    mSettingFragment = SettingFragment.newInstance(getResources().getString(R.string.string_setting));
                    fragmentTransaction.add(R.id.fl_content, mSettingFragment);
                } else {
                    fragmentTransaction.show(mSettingFragment);
                }
                mCurrentFragment = mSettingFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_setting));
                break;
            case R.id.nav_about:
                if (mAboutFragment == null) {
                    mAboutFragment = AboutFragment.newInstance(getResources().getString(R.string.string_about));
                    fragmentTransaction.add(R.id.fl_content, mAboutFragment);
                } else {
                    fragmentTransaction.show(mAboutFragment);
                }
                mCurrentFragment = mAboutFragment;
                mToolbar.setTitle(getResources().getString(R.string.string_about));
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.commit();
        return true;
    }

    @OnClick(R.id.ib_search_music)
    public void onViewClicked() {
        startActivity(SearchMusicActivity.class);
    }

}
