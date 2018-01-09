package com.rdc.musicplayer.musicplayer.model;

import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.OnLineLyrics;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicLrcContract;
import com.rdc.musicplayer.musicplayer.utils.GsonUtil;
import com.rdc.musicplayer.musicplayer.utils.OkHttpResultCallback;
import com.rdc.musicplayer.musicplayer.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;

import static com.rdc.musicplayer.musicplayer.constant.Constant.SEARCH_MUSIC_LRC;

public class GetMusicLrcModelImpl implements IGetMusicLrcContract.Model {

    @Override
    public void getLyrics(final IGetMusicLrcContract.Presenter presenter, int songId) {
        String url = SEARCH_MUSIC_LRC + songId;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                if (e != null) {
                    Log.e("error", e.getMessage());
                }
            }

            @Override
            public void onResponse(byte[] bytes) {
                try {
                    String response = new String(bytes, "utf-8");
                    OnLineLyrics onLineLyrics = GsonUtil.gsonToBean(response, OnLineLyrics.class);
                    if (onLineLyrics.getShowapi_res_code() == 0) {
                        OnLineLyrics.ShowapiResBodyBean showapiResBodyBean = onLineLyrics.getShowapi_res_body();
                        if (showapiResBodyBean.getRet_code() == 0) {
                            presenter.onGetOnlineLyricsSuccess(showapiResBodyBean.getLyric());
                        } else {
                            presenter.onGetOnlineLyricsFailed(response);
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
