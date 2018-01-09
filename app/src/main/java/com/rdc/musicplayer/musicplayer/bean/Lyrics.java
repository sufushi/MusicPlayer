package com.rdc.musicplayer.musicplayer.bean;

public class Lyrics {

    private String lrc;
    private long start;
    private long end;

    public Lyrics() {
    }

    public Lyrics(String lrc, long start, long end) {
        this.lrc = lrc;
        this.start = start;
        this.end = end;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
