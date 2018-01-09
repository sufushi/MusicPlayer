package com.rdc.musicplayer.musicplayer.model;

import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.OnLineMusic;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByKeywordContract;
import com.rdc.musicplayer.musicplayer.utils.GsonUtil;
import com.rdc.musicplayer.musicplayer.utils.OkHttpResultCallback;
import com.rdc.musicplayer.musicplayer.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;

import static com.rdc.musicplayer.musicplayer.constant.Constant.SEARCH_MUSIC_BY_KEYWORD;
import static com.rdc.musicplayer.musicplayer.constant.Constant.SEARCH_MUSIC_PAGE;

public class GetMusicByKeywordModelImpl implements IGetMusicByKeywordContract.Model {

    @Override
    public void getMusicByKeyword(final IGetMusicByKeywordContract.Presenter presenter, String keyword, int page) {
        String url = SEARCH_MUSIC_BY_KEYWORD + keyword + SEARCH_MUSIC_PAGE + page;
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
                    OnLineMusic onLineMusic = GsonUtil.gsonToBean(response, OnLineMusic.class);
                    if (onLineMusic.getShowapi_res_code() == 0) {
                        OnLineMusic.ShowapiResBodyBean showapiResBodyBean = onLineMusic.getShowapi_res_body();
                        if (showapiResBodyBean.getRet_code() == 0) {
                            OnLineMusic.ShowapiResBodyBean.PagebeanBean pagebeanBean = showapiResBodyBean.getPagebean();
                            List<OnLineMusic.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList = pagebeanBean.getContentlist();
                            presenter.onGetMusicByKeywordSuccess(contentlistBeanList);
                        } else {
                            presenter.onGetMusicByKeywordFailed(response);
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
