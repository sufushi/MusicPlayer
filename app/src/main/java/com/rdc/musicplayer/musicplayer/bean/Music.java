package com.rdc.musicplayer.musicplayer.bean;

import android.graphics.Bitmap;

public class Music {

    private int songId;
    private int singerId;
    private int albumId;
    private int seconds;
    private String songName;
    private String singerName;
    private String albumPath;
    private String albumBig;
    private String albumSmall;
    private String downUrl;
    private String url;
    private Bitmap thumbnail;

    public Music() {
    }

    public Music(int songId, int singerId, int albumId, int seconds, String songName,
                 String singerName, String albumBig, String albumSmall, String url) {
        this.songId = songId;
        this.singerId = singerId;
        this.albumId = albumId;
        this.seconds = seconds;
        this.songName = songName;
        this.singerName = singerName;
        this.albumBig = albumBig;
        this.albumSmall = albumSmall;
        this.url = url;
    }

    public Music(int songId, int singerId, int albumId, int seconds, String songName, String singerName,
                 String albumPath, String albumBig, String albumSmall, String downUrl, String url) {
        this.songId = songId;
        this.singerId = singerId;
        this.albumId = albumId;
        this.seconds = seconds;
        this.songName = songName;
        this.singerName = singerName;
        this.albumPath = albumPath;
        this.albumBig = albumBig;
        this.albumSmall = albumSmall;
        this.downUrl = downUrl;
        this.url = url;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumPath() {
        return albumPath;
    }

    public void setAlbumPath(String albumPath) {
        this.albumPath = albumPath;
    }

    public String getAlbumBig() {
        return albumBig;
    }

    public void setAlbumBig(String albumBig) {
        this.albumBig = albumBig;
    }

    public String getAlbumSmall() {
        return albumSmall;
    }

    public void setAlbumSmall(String albumSmall) {
        this.albumSmall = albumSmall;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Music{" +
                "songId=" + songId +
                ", singerId=" + singerId +
                ", albumId=" + albumId +
                ", seconds=" + seconds +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", albumPath='" + albumPath + '\'' +
                ", albumBig='" + albumBig + '\'' +
                ", albumSmall='" + albumSmall + '\'' +
                ", downUrl='" + downUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
