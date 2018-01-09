package com.rdc.musicplayer.musicplayer.model;

import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.MusicByCategory;
import com.rdc.musicplayer.musicplayer.contract.IGetMusicByCategoryContract;
import com.rdc.musicplayer.musicplayer.utils.GsonUtil;
import com.rdc.musicplayer.musicplayer.utils.OkHttpResultCallback;
import com.rdc.musicplayer.musicplayer.utils.OkHttpUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;

import static com.rdc.musicplayer.musicplayer.constant.Constant.SEARCH_MUSIC_BY_CATEGORY;

public class GetMusicByCategoryModelImpl implements IGetMusicByCategoryContract.Model {

    @Override
    public void getMusicListByCategory(final IGetMusicByCategoryContract.Presenter presenter, int category) {
        String url = SEARCH_MUSIC_BY_CATEGORY + category;
        Log.e("error", "url=" + url);
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
                    MusicByCategory musicByCategory = GsonUtil.gsonToBean(response, MusicByCategory.class);
                    if (musicByCategory.getShowapi_res_code() == 0) {
                        MusicByCategory.ShowapiResBodyBean showapiResBodyBean = musicByCategory.getShowapi_res_body();
                        if (showapiResBodyBean.getRet_code() == 0) {
                            MusicByCategory.ShowapiResBodyBean.PagebeanBean pagebeanBean = showapiResBodyBean.getPagebean();
                            List<MusicByCategory.ShowapiResBodyBean.PagebeanBean.SonglistBean> songlistBeanList = pagebeanBean.getSonglist();
                            presenter.onGetMusicListByCategorySuccess(songlistBeanList);
                        }

                    } else {
                        presenter.onGetMusicListByCategoryFailed(response);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
